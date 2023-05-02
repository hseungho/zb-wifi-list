<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        .loader {
            margin: auto;
            border: 8px solid #f3f3f3;
            border-radius: 50%;
            border-top: 8px solid #3498db;
            width: 50px;
            height: 50px;
            -webkit-animation: spin 2s linear infinite; /* Safari */
            animation: spin 2s linear infinite;
        }
        /* Safari */
        @-webkit-keyframes spin {
            0% { -webkit-transform: rotate(0deg); }
            100% { -webkit-transform: rotate(360deg); }
        }
        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    </style>
    <title>와이파이 정보 구하기</title>
</head>
<body>
    <div id="load_wifi_main_content" style="display: flex; flex-direction: column; border-collapse: collapse; margin-top: 30px"></div>

    <script>
        fetch('/wifi/exist', {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => {
                if (data.value) {
                    if (!confirm('이미 WIFI 데이터가 존재합니다. 기존 데이터를 삭제하시고 다시 로드하시겠습니까?\n\n주의: 기존 WIFI 데이터 삭제 시 관련 북마크 데이터도 삭제됩니다.')) {
                        history.go(-1);
                        return;
                    }
                }
                loadWifi();
            })
            .catch(err => console.log(err));

        function loadWifi() {
            const div = document.querySelector("#load_wifi_main_content");
            div.innerHTML = `
                            <h2 style='margin: auto;'>잠시 기다려주세요.</h2>
                            <br><br>
                            <div class="loader"></div>
                        `;
            fetch('/wifi/load', {method: 'GET'})
                .then(response => response.json())
                .then(data => {
                    div.innerHTML = '';
                    div.innerHTML = `
                                    <h2 style='margin: auto;'>${'${data.count}'}개의 WIFI 정보를 정상적으로 저장하였습니다.</h2>
                                    <br><br>
                                    <a href="/" style='margin: auto;'>홈으로 가기</a>
                                `;
                })
                .catch(err => {
                    console.log(err);
                    alert(err);
                });
        }
    </script>
</body>
</html>
