import MVController.FriendshipController;
import MVController.UserController;
import Models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import db.DBConnector;
import db.DBQuizCommunicator;
import db.DBUserCommunicator;
import db.DBFriendshipCommunicator;

import Models.Quiz;


import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Usera {
    public static void main(String[] args) throws SQLException, JsonProcessingException {

        DBConnector dbCon = new DBConnector();
//        DBUserCommunicator user = new DBUserCommunicator(dbCon.getCon());
//        DBFriendshipCommunicator friends = new DBFriendshipCommunicator(dbCon.getCon());
//
//        String user1 = "rame 1";
//        String user2 = "rame 2";
//
//        if(user.createUser(user1, "rume", "rime")) System.out.println("User " + user1 + " created");
//        else System.out.println("User " + user1 + " not created");
//
//        if(user.createUser(user2, "rume", "rime")) System.out.println("User " + user2 + " created");
//        else System.out.println("User " + user2 + " not created");
//
//        if(user.checkUserExists("rame 1"))System.out.println("User rame 1 exists");
//        if(user.checkUserExists("rame 2"))System.out.println("User rame 2 exists");
//        int user1id = user.getCreatorId(user1);
//        int user2id = user.getCreatorId(user2);
//
//        System.out.println("user1 id = " + user1id);
//        System.out.println("user2 id = " + user2id);
//
//        friends.createRequest(user1id, user2id);
//        System.out.println("user 2 received " + friends.getReceivedRequest(user2id));
//        System.out.println("user 1 sent "     + friends.getSentRequest(user1id));
//        System.out.println("user 2 friends "  + friends.getFriends(user2id));
//        System.out.println("user 1 friends "  + friends.getFriends(user1id));
//
//        friends.getFriendshipstatus(user2id, user1id);
//
//        friends.changeStatus(user1id, user2id, DBFriendshipCommunicator.FriendshipStatus.FRIENDSHIP_STATUS_FRIENDS);
//        System.out.println("user 2 received " + friends.getReceivedRequest(user2id));
//        System.out.println("user 1 sent "     + friends.getSentRequest(user1id));
//        System.out.println("user 2 friends "  + friends.getFriends(user2id));
//        System.out.println("user 1 friends "  + friends.getFriends(user1id));
//
//        User usr = new User("saxeli", "gvari", 1);
//        System.out.println(usr.toString());
//
//        FriendInt frnd = new User("megobari", "gvara", 2);
//        System.out.println("username " + frnd.getUsername());
//        System.out.println("ID " + frnd.getId());
//
//
//        // use mvc controller and models
//        System.out.println("\r\n\n\n<<<<<<<<<<<<< use of mvc and models >>>>>>>>>>>>>\r\n\n\n");
//        UserController uController = new UserController(dbCon);
//        FriendshipController fcontroller = new FriendshipController(dbCon);
//
//        User newUser1 = uController.createUser("user1", "password1", "secret1");
//        User newUser2 = uController.createUser("user2", "password2", "secret2");
//        User newUser3 = uController.createUser("user3", "password3", "secret3");
//
//        if(newUser1 != null) System.out.println("user 1 created");
//        else newUser1 = uController.loginUser("user1", "password1");
//
//        if(newUser2 != null) System.out.println("user 2 created");
//        else newUser2 = uController.loginUser("user2", "password2");
//
//        if(newUser3 != null) System.out.println("user 3 created");
//        else newUser3 = uController.loginUser("user3", "password3");
//
//        if(newUser1 == null || newUser2 == null || newUser3 == null) {
//            System.out.println("Error while login user");
//            return;
//        }
//
//
////        if(uController.changePasswordRequest("user1", "pass1", "secret1")) System.out.println("user 1 changed password");
////        if(uController.changePasswordRequest("user2", "pass2", "secret2")) System.out.println("user 2 changed password");
////        if(uController.changePasswordRequest("user3", "pass3", "secret3")) System.out.println("user 2 changed password");
//
//
//
//        if(fcontroller.sendFriendship(newUser1, newUser2)){
//            System.out.println("user 1 sent friendship");
//        }
//        if(fcontroller.approveFriendship(newUser2, newUser1)){
//            System.out.println("user 2 approved friendship");
//        }
//
//        if(fcontroller.sendFriendship(newUser3, newUser2)){
//            System.out.println("user 3 sent friendship");
//        }
//        if(fcontroller.rejectFriendship(newUser2, newUser3)){
//            System.out.println("user 2 rejected friendship");
//        }
//
//        System.out.println(newUser1);
//        System.out.println(newUser2);
//        System.out.println(newUser3);
//
//        fcontroller.fillUserRelations(newUser1);
//        fcontroller.fillUserRelations(newUser2);
//        fcontroller.fillUserRelations(newUser3);
//
//        System.out.println(newUser1);
//        System.out.println(newUser2);
//        System.out.println(newUser3);

        // quiz databases
        System.out.println("\r\n\n\n<<<<<<<<<<<<< quiz datatables >>>>>>>>>>>>>\r\n\n\n");

        HashMap<QuestionType, QuestionParameters> questions = new HashMap<QuestionType, QuestionParameters>();

        QuestionParameters qParams1 = new QuestionParameters(123, 7, 1);
        QuestionParameters qParams2 = new QuestionParameters(123, 7, 2);
        QuestionParameters qParams3 = new QuestionParameters(123, 7, 3);
        QuestionParameters qParams4 = new QuestionParameters(123, 7, 4);

        QuestionResponse q1 = new QuestionResponse();
        q1.setQuestion("Who was President during the Bay of Pigs fiasco?");
        q1.setResponse("John F. Kennedy");

        FillInTheBlank q2 = new FillInTheBlank();
        q2.setQuestion("One of President Lincoln’s most famous speeches was the __________ Address.");
        q2.setResponse("Gettysburg");

        MultipleChoice q3 = new MultipleChoice();
        q3.setQuestion("What is the capital of France?");
        q3.setChoices(new String[]{"A. Paris", "B. Rome", "C. Madrid"});
        q3.setResponse("A. Paris");

        PictureResponse q4 = new PictureResponse();
        q4.setQuestion("/path/to/image.jpg");
        q4.setResponse("Bald Eagle");

        questions.put(q1, qParams1);
        questions.put(q2, qParams2);
        questions.put(q3, qParams3);
        questions.put(q4, qParams4);

        boolean randomQuestion = true;
        boolean immediateAnswer = true;
        boolean multiplePageQuiz = false;

        int id = 2;
        int userId = 1;

        String name = "slay quiz";
        String description = "slay quiz is cool";

        Quiz quiz = new Quiz();

        quiz.setRandomQuestion(randomQuestion);
        quiz.setImmediateAnswer(immediateAnswer);
        quiz.setMultiplePageQuiz(multiplePageQuiz);
        quiz.setId(id);
        quiz.setUserId(userId);
        quiz.setName(name);
        quiz.setDescription(description);
        quiz.setCreate_time(new Date(System.currentTimeMillis()));
        quiz.setQuestions(questions);

        DBQuizCommunicator quizCommunicator = new DBQuizCommunicator(dbCon.getCon());

        if(quizCommunicator.createQuiz(quiz))   System.out.println("quiz created");
        else                                    System.out.println("quiz Already exist");

        if(quizCommunicator.checkQuizExists(quiz.getName())){
            System.out.println("\r\n\n\nquiz exist");

            Quiz newQuiz = quizCommunicator.getQuizByName(quiz.getName());
            System.out.println("get quiz :" + newQuiz.toString());

            HashMap<QuestionType, QuestionParameters> map = newQuiz.getQuestions();
            Set<QuestionType> set = map.keySet();

            for(QuestionType q : set){
                System.out.println(q.toString());
                System.out.println(map.get(q).toString());
            }


        }else{
            System.out.println("quiz dont exist");
        }

        if(quiz != null)System.out.println("created quiz " + quiz.toString());

    }
}


//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.core.type.TypeReference;
//import Models.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Usera {
//    public static void main(String[] args) {
//        String jsonString = """
//                                {
//                                "PictureResponse{\\"imagePath\\":\\"/path/to/image.jpg\\",\\"response\\":\\"Bald Eagle\\"}":\s
//                                    {"id": 4, "score": 7, "timeSec": 123},\s
//                                "QuestionResponse{\\"question\\":\\"Who was President during the Bay of Pigs fiasco?\\", \\"response\\":\\"John F. Kennedy\\"}":\s
//                                    {"id": 1, "score": 7, "timeSec": 123},\s
//                                "FillInTheBlank{\\"question\\":\\"One of President Lincoln’s most famous speeches was the __________ Address.\\", \\"response\\":\\"Gettysburg\\"}":\s
//                                    {"id": 2, "score": 7, "timeSec": 123},\s
//                                "MultipleChoice{\\"question\\":\\"What is the capital of France?\\", \\"choices\\":[\\"A. Paris\\", \\"B. Rome\\", \\"C. Madrid\\"], \\"correctAnswer\\":\\"A. Paris\\"}":\s
//                    {"id": 3, "score": 7, "timeSec": 123}
//                }
//
//        """;
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            Map<String, QuestionParameters> map = objectMapper.readValue(jsonString, new TypeReference<Map<String, QuestionParameters>>() {});
//            HashMap<QuestionType, QuestionParameters> questionMap = new HashMap<>();
//
//            for (Map.Entry<String, QuestionParameters> entry : map.entrySet()) {
//                String key = entry.getKey();
//                QuestionParameters value = entry.getValue();
//
//                if (key.startsWith("PictureResponse")) {
//                    PictureResponse pictureResponse = objectMapper.readValue(key.substring(15), PictureResponse.class);
//                    questionMap.put(pictureResponse, value);
//                } else if (key.startsWith("QuestionResponse")) {
//                    QuestionResponse questionResponse = objectMapper.readValue(key.substring(16), QuestionResponse.class);
//                    questionMap.put(questionResponse, value);
//                } else if (key.startsWith("FillInTheBlank")) {
//                    FillInTheBlank fillInTheBlank = objectMapper.readValue(key.substring(14), FillInTheBlank.class);
//                    questionMap.put(fillInTheBlank, value);
//                } else if (key.startsWith("MultipleChoice")) {
//                    MultipleChoice multipleChoice = objectMapper.readValue(key.substring(14), MultipleChoice.class);
//                    questionMap.put(multipleChoice, value);
//                }
//            }
//
//            // Printing the map to verify
//            for (Map.Entry<QuestionType, QuestionParameters> entry : questionMap.entrySet()) {
//                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
