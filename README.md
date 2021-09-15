# 2021-September-Mp3Player
# [![WebApp](https://user-images.githubusercontent.com/72185011/122542544-c62c5300-d065-11eb-9f03-5249a0588c9b.jpg)](https://www.youtube.com/watch?v=Tt3UGV4Hz9I)

<h1 align="center"> Dotori Music </h1> 
<h3 align="center"> Spring Boot project </h3>
<h5 align="center"> Web Audio project - (September 2021~) </h5>

<p align="center"> 
  <iframe src='https://gfycat.com/ifr/VastFluffyBalloonfish' frameborder='0' scrolling='no' allowfullscreen width='640' height='402'></iframe>
  <img src="https://user-images.githubusercontent.com/72185011/121696411-0429f400-cb07-11eb-8d8c-9d35b55c83a6.gif" alt="Animated gif pacman game" height="300px" width="800">
</p>


<!-- TABLE OF CONTENTS -->
<h2 id="table-of-contents"> :book: Table of Contents</h2>

<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#about-the-project"> ➤ About The Project</a></li>
    <li><a href="#demo"> ➤ Demo link</a></li>
    <li><a href="#buildwith"> ➤ Build with</a></li>
    <li><a href="#db"> ➤ DB </a></li>
    <li><a href="#main"> ➤ Main </a></li>
    <li><a href="#upload"> ➤ Upload </a></li>
    <li><a href="#login"> ➤ Login </a></li>
    <li><a href="#todo"> ➤ Wish functions </a></li>
  </ol>
</details>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- ABOUT THE PROJECT -->
<h2 id="about-the-project"> :pencil: About The Project and OverView</h2>

<p align="justify"> 
  First project using SPRING MVC and JSP, motivated by reddit community. There are basic concepts of CRUD such as board, reply and security login and paging(mybatis), apply good example of commit and readme style by many examples. I used Bootstrap design for this project and this UI/UX remind me reddit.com. I tried to apply programming logic and domain constructor using his book, [코드로 배우는 스프링 웹](http://www.yes24.com/Product/Goods/64340061) and studied basic Spring framework in Udemy by Chard Darby. 
</p>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- DEMO -->
<h2 id="demo"> :cloud: Demo</h2>
Here is a working live demo : http://ec2-52-79-247-18.ap-northeast-2.compute.amazonaws.com:8080 (not perfectly work)

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- PROJECT FILES DESCRIPTION -->
<h2 id="buildwith"> :floppy_disk: Project Files Description</h2>

- [jQuery - Ajax](http://www.w3schools.com/jquery/jquery_ref_ajax.asp) 
- [Bootstrap](https://themeforest.net/item/ask-me-responsive-questions-and-answers-template/6357488) - Ask me - Responsive Questions and Answers Template
- [Hodor 403](https://www.google.com/url?sa=i&url=https%3A%2F%2Fcodepen.io%2FYasio%2Fpen%2FxamBVV&psig=AOvVaw2MOE1UmR4fnHeU7-6m6arN&ust=1624097984837000&source=images&cd=vfe&ved=0CAoQjRxqFwoTCMje1836oPECFQAAAAAdAAAAABAD)
- [coffee maker machine](https://www.google.com/url?sa=i&url=https%3A%2F%2Fcodepen.io%2Fthisisroger%2Fpen%2FmskhL&psig=AOvVaw2_b_Q1IQLr64O0J3C0naFz&ust=1624098028147000&source=images&cd=vfe&ved=0CAoQjRxqFwoTCMCcnuP6oPECFQAAAAAdAAAAABAI)

- Spring framework 5.x
- Java 1.8
- JSP
- MySql 8.x
- Tomcat 9.0

- Amazon AWS EC2
![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- PROJECT FILES DESCRIPTION -->
<h2 id="buildwith"> :floppy_disk: Main functions</h2>

<ul>
  Board
  <li><b>MVC pattern CRUD</li>
</ul>
  
<ul>  
  Reply
  <li>Ajax Asynchronous</li>
  <li>REST API.</li>
</ul>
  
<ul>
  Paging
  <li>Mybatis limit, criteria</li>
  <li>learned hidden input trick to send page&amount value</li>
</ul>

<ul>  
  Login
  <li>Spring security, devided role User and Admin function and contents </li>
  <li>Register validatoin with regular expression</li>
  <li>Email verification using java mailsender</li>
</ul>


![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)
<!-- DEMO -->
<h2 id="db"> :cloud: DB</h2>
https://www.erdcloud.com/d/bGowZZX5AzfXe7vcg

![](https://user-images.githubusercontent.com/72185011/122542788-0ab7ee80-d066-11eb-86ca-8c5fcf1a2842.jpg)

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- DEMO -->
<h2 id="main"> :cloud: MainPage</h2>

![](https://user-images.githubusercontent.com/72185011/122543059-55396b00-d066-11eb-89b1-c3c6363a2185.jpg)

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- DEMO -->
<h2 id="board"> :cloud: Board</h2>
This is board

![](https://user-images.githubusercontent.com/72185011/122543265-8dd94480-d066-11eb-82c1-b8eb762bddb2.jpg)

![](https://user-images.githubusercontent.com/72185011/122544117-7189d780-d067-11eb-906d-7b882d5abdf4.gif)

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- DEMO -->
<h2 id="admin"> :cloud: Admin</h2>
only admin user can see remove button on the list and remove any post 

![](https://user-images.githubusercontent.com/72185011/122545472-d7c32a00-d068-11eb-9b3f-0ee025093b52.jpg)

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<h2 id="reply"> :cloud: Reply</h2>
hierarchy comments was most picky function using mysql query

![](https://user-images.githubusercontent.com/72185011/122544433-c6c5e900-d067-11eb-863f-c73d767b91de.jpg)

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- DEMO -->
<h2 id="login"> :cloud: Login</h2>


![](https://user-images.githubusercontent.com/72185011/122545229-903c9e00-d068-11eb-89d2-6a8c2e47207d.jpg)

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- DEMO -->
<h2 id="register"> :cloud: Register</h2>



![](https://user-images.githubusercontent.com/72185011/122545263-9c286000-d068-11eb-98f9-e550e5023fb9.jpg)

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<!-- DEMO -->
<h2 id="error"> :cloud: ERROR PAGE 403</h2>



![](https://user-images.githubusercontent.com/72185011/122545327-ae0a0300-d068-11eb-8898-39fb77948531.gif)

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

<h2 id="todo"> :cloud: Wish functions(ToDo)</h2>
<ul>
  <li></li>
</ul>
