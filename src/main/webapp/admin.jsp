<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 20px;
        }

        .form-container {
            margin-bottom: 20px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            max-width: 400px;
            width: 100%;
            display: inline-block;
            vertical-align: top;
        }

        .form-container h2 {
            font-size: 24px;
            margin-bottom: 20px;
            text-align: center;
            color: #333333;
        }

        .form-container form {
            display: flex;
            flex-direction: column;
        }

        .form-container input[type="text"], .form-container input[type="submit"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #dddddd;
            border-radius: 4px;
            font-size: 16px;
            transition: border-color 0.3s ease;
        }

        .form-container input[type="submit"] {
            background-color: #fa3c3c;
            color: #ffffff;
            border: none;
            cursor: pointer;
        }

        .form-container input[type="submit"]:hover {
            background-color: #b30202;
        }
    </style>
</head>
<body>
    <div class="form-container" style="float: left;">
        <h2>Delete User</h2>
        <form action="adminDeleteUser" method="post">
            <input type="text" name="username" placeholder="Enter username...">
            <input type="submit" value="Delete">
        </form>
    </div>

    <div class="form-container" style="float: left; margin-left: 20px;">
        <h2>Delete Quiz</h2>
        <form action="admin" method="post">
            <input type="text" name="quizName" placeholder="Enter quiz name...">
            <input type="submit" value="Delete">
        </form>
    </div>
</body>
</html>
