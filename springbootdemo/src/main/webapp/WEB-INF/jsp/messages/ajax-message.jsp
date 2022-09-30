<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="UTF-8">
    <title>Ajax Page</title>
  </head>

  <body>
    <jsp:include page="../layout/navbar.jsp" />

    <div class="container">
      <h1>Ajax + Restful</h1>

      <input id="myMessage">
      <button id="submitBtn">送出</button>

      <div>
        <table class="mytable" id="list_table_json">
          <thead>
            <tr>
              <th>留言內容</th>
              <th>留言時間</th>
            </tr>
          </thead>
        </table>
      </div>
    </div>

    <script type="text/javascript">
      $(document).ready(function () {
        $('#submitBtn').click(function () {
          var inputText = document.getElementById('myMessage').value;
          var dtoObject = { "text": inputText };  // JavaScript 物件
          var dtoJsonString = JSON.stringify(dtoObject);  // 將 JavaScript 物件轉換為 JSON 字串

          $.ajax({
            url: 'http://localhost:8081/my-app/messages/api/post',
            contentType: 'application/json ; charset=UTF-8',  // 送過去的格式(預設)
            dataType: 'json', // 傳回來的格式
            method: 'post',
            data: dtoJsonString,
            success: function (result) {
              console.log(result)

              $('#list_table_json tbody tr td').remove();

              msg_data = '<tbody>';
              $.each(result, function (index, value) {
                console.log(value.text)
                msg_data += '<tr>'
                msg_data += '<td>' + value.text + '</td>'
                msg_data += '<td>' + value.added + '</td>'
                msg_data += '</tr>'
              })
              msg_data += '</tbody>';

              $('#list_table_json').append(msg_data);


            },
            error: function (err) {
              console.log(err)
            }
          })



        })
      })



    </script>






  </body>

  </html>