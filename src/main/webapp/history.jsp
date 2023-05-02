<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="css/table.css">
</head>
<script>
    fetch('/history', {method:'GET'})
        .then(response => response.json())
        .then(data => {
            const histories = data;
            console.log(histories)
            const tbody = document.querySelector('table tbody');
            tbody.innerHTML = '';
            if (histories.length > 0) {
                histories.forEach(history => {
                    const tr = document.createElement('tr');
                    tr.innerHTML = `
                      <td>${'${history.id}'}</td>
                      <td>${'${history.lat}'}</td>
                      <td>${'${history.lnt}'}</td>
                      <td>${'${history.createdAt}'}</td>
                      <td style="text-align: center"><button data-id='${'${history.id}'}' onclick='deleteHistory()'>삭제</button></td>
                    `;
                    tbody.appendChild(tr);
                });
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('HISTORY 정보를 가져오는 중 오류가 발생했습니다.');
        });

    function deleteHistory() {
        const id = event.target.dataset.id;
        const url = encodeURI(`/history?id=${'${id}'}`);
        fetch(url, { method: 'DELETE' })
            .then((res) => {
                if (res.ok) {
                    alert('삭제되었습니다.')
                    window.location.reload();
                } else {
                    if (res.status === 400) {
                        alert('잘못된 요청입니다.')
                    } else {
                        alert(`요청에 실패하였습니다.\nSTATUS: ${'${res.status}}'}`)
                    }
                }
            })
            .catch(err => alert(err));
    }
</script>
<body>
    <h1>위치 히스토리 목록</h1>
    <a href="/">홈</a> | <a href="/history.jsp">위치 히스토리 목록</a> | <a href="/load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    <br><br>
    <table>
        <thead>
        <tr style="height: 30px">
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <!-- HISTORY DATA SEGMENT -->
        </tr>
        </tbody>
    </table>
</body>
</html>
