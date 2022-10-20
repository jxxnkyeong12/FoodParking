<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
/* .wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
}


.content {
  font-family: system-ui, serif;
  font-size: 2rem;
  padding: 3rem;
  border-radius: 1rem;
  background: #ff6e6c;
}
 */

body{margin-top:20px;
 justify-content: center;}
.cover-background {
    position: relative !important;
    background-size: cover !important;
    overflow: hidden !important;
    background-position: center !important;
    background-repeat: no-repeat !important;
}
.p-0 {
    padding: 0!important;
}
section {
    padding: 120px 0;
    overflow: hidden;
    background: #fff;
}

.error-page {
    background-color: #BABABA4A;
    -webkit-backdrop-filter: blur(9px);
    backdrop-filter: blur(15px);
    border: 1px solid rgba(234,234,235,0.2);
    padding: 80px 20px;
}
.text-center {
    text-align: center!important;
}
.error-page h1 {
    font-size: 200px;
    line-height: 1;
    font-weight: 600;
}
.text-secondary {
    color: #15395A !important;
}
.mb-4 {
    margin-bottom: 1.5rem!important;
}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section class="p-0 bg-img cover-background" style="background-color: #fff !important;">
        <div class="container-fluid d-flex flex-column">
            <div class="row align-items-center justify-content-center min-vh-100">
                <div class="col-md-9 col-lg-6 my-5" style="background-color: #fff !important;">
                    <div class="text-center error-page">
                        <h1 class="mb-0 text-secondary" style="color:  #F25C05 !important;">404</h1>
                        <h2 class="mb-4 text-white">요청하신 페이지를 찾을수 없거나 없는 페이지 입니다!</h2>
                        <p class="w-sm-80 mx-auto mb-4 text-white">This page is incidentally inaccessible because of support. We will back very before long much obliged for your understanding</p>
                        <div class = 'btnSet'>
                            <a href="/webcteam" class="btn-fill ">홈으로 돌아가기</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

</body>
</html>