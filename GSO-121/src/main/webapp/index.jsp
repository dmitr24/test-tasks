<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>GSO-121 file uploader</title>
</head>
<body>
<main>
    <div>
        <h1>File uploading form</h1>
    </div>
    <form action="fileServlet" method="POST" enctype = "multipart/form-data">
        <input type="file" name="file">
        <br>
        <button type="submit">Upload new file</button>
    </form>
</main>
</body>
</html>