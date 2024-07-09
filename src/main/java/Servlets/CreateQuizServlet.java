package Servlets;

import MVController.UserController;
import Models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.DBQuizCommunicator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateQuizServlet  extends HttpServlet {
    private static final String WHOAMI = "whoami";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("createQuiz.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBQuizCommunicator quizCommunicator = (DBQuizCommunicator) getServletContext().getAttribute("quizCommunicator");
        UserController userController = (UserController) getServletContext().getAttribute("userController");


        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } finally {
            reader.close();
        }

        Quiz quiz = new Quiz();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(sb.toString());
        quiz.setName(jsonNode.get("name").asText());
        quiz.setDescription(jsonNode.get("description").asText());
        quiz.setCreate_time(new Date(System.currentTimeMillis()));
        quiz.setRandomQuestion(jsonNode.get("description").asBoolean());
        quiz.setMultiplePageQuiz(jsonNode.get("modePages").asBoolean());
        quiz.setImmediateAnswer(jsonNode.get("modeImmediate").asBoolean());

        Cookie[] cookies = req.getCookies();
        Cookie infoCookie = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(WHOAMI)) {
                    infoCookie = cookie;
                }
            }
        }

        User myuser = userController.getUserInfo(infoCookie.getValue());
        quiz.setUserId(myuser.getId());

        String jsonArray = jsonNode.get("questions").asText();
        if(!deserializeReceivedQuiz(jsonArray, quiz)) {
            return;
        }

        try {
            if(quizCommunicator.createQuiz(quiz)){
                resp.setStatus(200);
            }else{
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Can't create quiz");
            }
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Can't create quiz");
            throw new RuntimeException(e);
        }
    }

    private boolean deserializeReceivedQuiz(String quizDataJson, Quiz quiz) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode arrNode = objectMapper.readTree(quizDataJson);
        HashMap<QuestionType, QuestionParameters> questionMap = new HashMap<>();

        if (arrNode.isArray()) {
            for (JsonNode objNode : arrNode) {
                String type = objNode.get("type").asText();
                QuestionType temp = null;
                if (type.equals("PictureResponse")) {
                    temp = new PictureResponse(objNode.get("imagePath").asText(), objNode.get("response").asText());
                } else if (type.equals("QuestionResponse")) {
                    temp = new QuestionResponse(objNode.get("question").asText(), objNode.get("response").asText()) ;
                } else if (type.equals("FillInTheBlank")) {
                    temp = new FillInTheBlank(objNode.get("question").asText(), objNode.get("response").asText()) ;
                } else if (type.equals("MultipleChoice")) {
                    JsonNode choicesArr = objNode.get("choices");
                    ArrayList<String> arr = new ArrayList<>();

                    for(JsonNode node : choicesArr){
                        arr.add(node.asText());
                    }
                    String lastArr[] = arr.toArray(new String[arr.size()]);



                    temp = new MultipleChoice(objNode.get("question").asText(), lastArr, objNode.get("correctAnswer").asText()) ;
                }
                JsonNode params = objNode.get("questionParams");
                questionMap.put(temp, new QuestionParameters(Integer.parseInt(params.get("timeSec").asText()),
                                                             Integer.parseInt(params.get("score").asText()),
                                                             Integer.parseInt(params.get("index").asText())));
            }
        }

        quiz.setQuestions(questionMap);

        return true;
    }
}
