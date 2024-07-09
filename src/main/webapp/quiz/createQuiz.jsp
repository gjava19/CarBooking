<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Quiz</title>
    <link rel = "stylesheet" href = ../styles/quizCreate.css>
</head>
<body>
<jsp:include page="../Header.jsp"/>
<main>
    <div class="container">
        <h2>Create Quiz</h2>
        <form id="quizForm">
            <label for="name">Quiz Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4"></textarea>

            <label for="modeRandom">Mode Random:</label>
            <select id="modeRandom" name="modeRandom">
                <option value="true">True</option>
                <option value="false">False</option>
            </select>

            <label for="modePages">Mode Pages:</label>
            <select id="modePages" name="modePages">
                <option value="true">True</option>
                <option value="false">False</option>
            </select>

            <label for="modeImmediate">Mode Immediate:</label>
            <select id="modeImmediate" name="modeImmediate">
                <option value="true">True</option>
                <option value="false">False</option>
            </select>

            <h3>Questions</h3>
            <div id="questions">

            </div>
            <button type="button" id="addQuestion">Add Question</button>

            <button type="submit">Create Quiz</button>
        </form>
    </div>
</main>

<script>
    const questionsContainer = document.getElementById('questions');
    const addQuestionButton = document.getElementById('addQuestion');

    addQuestionButton.addEventListener('click', () => {
        const questionDiv = document.createElement('div');
        questionDiv.classList.add('question');

        questionDiv.innerHTML = `
                <label for="questionType">Question Type:</label>
                <select name="questionType" class="questionType">
                    <option value="QuestionResponse">Question Response</option>
                    <option value="PictureResponse">Picture Response</option>
                    <option value="MultipleChoice">Multiple Choice</option>
                    <option value="FillInTheBlank">Fill in the Blank</option>
                </select>

                <div class="questionDetails"></div>

                <p>
                    <label for="Index">Index:</label>
                    <input type="number" name="index" required>
                </p>
                <p>
                    <label for="score">Score:</label>
                    <input type="number" name="score" required>
                </p>
                <p>
                    <label for="timeSec">Time (sec):</label>
                    <input type="number" name="timeSec" required>
                </p>
                <button type="button" class="removeQuestion">Remove Question</button>
            `;

        questionDiv.querySelector('.questionType').addEventListener('change', (e) => {
            const questionDetails = questionDiv.querySelector('.questionDetails');
            const type = e.target.value;

            let detailsHTML = '';
            switch (type) {
                case 'QuestionResponse':
                    detailsHTML = `
                            <p>
                                <label for="question">Question:</label>
                                <input type="text" name="question" required>
                            </p>
                            <p>
                                <label for="response">Response:</label>
                                <input type="text" name="response" required>
                            </p>
                        `;
                    break;
                case 'PictureResponse':
                    detailsHTML = `
                            <p>
                                <label for="imagePath">Image Path:</label>
                                <input type="text" name="imagePath" required>
                            </p>
                            <p>
                                <label for="response">Response:</label>
                                <input type="text" name="response" required>
                            </p>
                        `;
                    break;
                case 'MultipleChoice':
                    detailsHTML = `
                            <p>
                                <label for="question">Question:</label>
                                <input type="text" name="question" required>
                            </p>
                            <p>
                                <label for="choices">Choices (comma separated):</label>
                                <input type="text" name="choices" required>
                            </p>
                            <p>
                                <label for="correctAnswer">Correct Answer:</label>
                                <input type="text" name="correctAnswer" required>
                            </p>
                        `;
                    break;
                case 'FillInTheBlank':
                    detailsHTML = `
                            <p>
                                <label for="question">Question:</label>
                                <input type="text" name="question" required>
                            </p>
                            <p>
                                <label for="response">Response:</label>
                                <input type="text" name="response" required>
                            </p>
                        `;
                    break;
            }

            questionDetails.innerHTML = detailsHTML;
        });

        questionDiv.querySelector('.removeQuestion').addEventListener('click', () => {
            questionDiv.remove();
        });

        questionsContainer.appendChild(questionDiv);
    });

    document.getElementById('quizForm').addEventListener('submit', function(event) {
        event.preventDefault();

        const questions = [];
        const questionDivs = questionsContainer.querySelectorAll('.question');

        questionDivs.forEach(div => {
            const type = div.querySelector('.questionType').value;
            const index = div.querySelector('input[name="index"]').value;
            const score = div.querySelector('input[name="score"]').value;
            const timeSec = div.querySelector('input[name="timeSec"]').value;
            const questionDetails = div.querySelector('.questionDetails');
            const questionParams = { index: parseInt(index), score: parseInt(score), timeSec: parseInt(timeSec) };

            let questionObj;
            switch (type) {
                case 'QuestionResponse':
                    questionObj = {
                        type,
                        question: questionDetails.querySelector('input[name="question"]').value,
                        response: questionDetails.querySelector('input[name="response"]').value,
                        questionParams
                    };
                    break;
                case 'PictureResponse':
                    questionObj = {
                        type,
                        imagePath: questionDetails.querySelector('input[name="imagePath"]').value,
                        response: questionDetails.querySelector('input[name="response"]').value,
                        questionParams
                    };
                    break;
                case 'MultipleChoice':
                    questionObj = {
                        type,
                        question: questionDetails.querySelector('input[name="question"]').value,
                        choices: questionDetails.querySelector('input[name="choices"]').value.split(','),
                        correctAnswer: questionDetails.querySelector('input[name="correctAnswer"]').value,
                        questionParams
                    };
                    break;
                case 'FillInTheBlank':
                    questionObj = {
                        type,
                        question: questionDetails.querySelector('input[name="question"]').value,
                        response: questionDetails.querySelector('input[name="response"]').value,
                        questionParams
                    };
                    break;
            }

            questions.push(questionObj);
        });

        fetch('create', {
            method: 'POST',
            body: JSON.stringify({
                name: this.querySelector('input[name="name"]').value,
                description: this.querySelector('textarea[name="description"]').value,
                modeRandom: this.querySelector('select[name="modeRandom"]').value,
                modePages: this.querySelector('select[name="modePages"]').value,
                modeImmediate: this.querySelector('select[name="modeImmediate"]').value,
                questions: JSON.stringify(questions)
            })
        })
    });
</script>
</body>
</html>
