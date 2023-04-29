# Foodie Android Application
<p align="center">
  <img src="https://user-images.githubusercontent.com/63088252/235286890-088cde89-9dee-4c0f-99d6-09bb126a50b7.png" />
  </p>
  
Foodie Restaurant is a Kotlin android application for getting orders from customers and delivering desired dishes to them. <br />
I have implemented both: <br />
- **Backend:**<br />
  For the backend, I use Ktor, JWT Authentication, Hashing method for user password, and H2 Database. Here is the link to Foodie Ktor backend codes:
  
  <br />
 - **Android Application:** <br />
   It makes use of the following libraries and technologies: <br />
    - Clean Architecture
    - MVVM
    - Dagger Hilt 
    - Room
    - Retrofit
    - OTP Authentication Service
    - Jetpack Compose
    - Navigation
    - Data Store Preferences
    - Coroutine
    - Channel
    - StateFlow
    - Glide

### 📺 App Screenshots & Features:
<br />
<b>Splash Screen & Welcome pages</b> <br /><br /> 
<p align="center"><img src="https://user-images.githubusercontent.com/63088252/235288261-054fec97-8f74-4ea3-ba90-dbdaaa009d91.jpg" /><br /> 
  <img src="https://user-images.githubusercontent.com/63088252/235288265-f483f609-0514-41d4-9bbe-e3f94ecc30a6.jpg" /> <img src="https://user-images.githubusercontent.com/63088252/235288271-33da11b1-9e7a-4505-ad19-98c36f5e54c1.jpg" />  <img src="https://user-images.githubusercontent.com/63088252/235288273-f515c995-e190-448f-ba2f-57142308588c.jpg" /> <img src="https://user-images.githubusercontent.com/63088252/235288274-34e549bd-efba-4678-b3a9-2a4cc036d9e8.jpg" /></p>
<br />

<br />
<b>Sign UP:</b><br /><br /> 
  After entering the phone number by users, with the help of OTP service, they will be authorized to be redirected to Sign up infromation page. Next that they would log in to the Home Page.<br /> <br /><br /> 
<p align="center"><img src="https://user-images.githubusercontent.com/63088252/235296812-103e2537-a521-4759-82bb-d20bdb6d8668.jpg" />
  <img src="https://user-images.githubusercontent.com/63088252/235296815-f88e6680-7065-4bfe-a9a2-61ed500ccbd6.jpg" /> <img src="https://user-images.githubusercontent.com/63088252/235296825-d19bfcf5-2747-48ba-946e-5b0d2d91d257.jpg" /></p>
<br />


<br />
<b>Log in: </b> <br /><br /> 
  Users can log in with their phone number and password and also will be logged in unless they logout manually <br /><br /> <br />
<p align="center"><img src="https://user-images.githubusercontent.com/63088252/235296884-2332d5aa-2ca2-487a-abca-b8f8f004b125.jpg" /><br /> 
<br />

  
<br />
<p text-align: left> <b>Main Pages of Application: </b> <br /><br /> 
  When Users log in, the data of different dishes will be loaded from Ktor Server. Now users can see their favorite foods, menus, and dishes. Also, they can go through other screens like Shopping Cart, Profile, Current orders, and Purchase History:</p><br /><br /> 
<p align="center"><img src="https://user-images.githubusercontent.com/63088252/235294173-94f28bdb-58b3-4669-8f51-ba965806438e.jpg" />
  <img src="https://user-images.githubusercontent.com/63088252/235294183-7d4e7d81-6665-4b6a-bb29-69e84ac60da2.jpg" /> <img src="https://user-images.githubusercontent.com/63088252/235294193-f98e3184-3cb1-426f-b26c-22ab55a8dcaa.jpg" /> <img src="https://user-images.githubusercontent.com/63088252/235296901-9bca0689-6616-447b-95d3-ad0642e1045d.jpg" /></p>
<br />



<br />
<p text-align: left> <b>Oreder Food Procces </b> <br /><br /> 
User will choose their order, then, by going to the shopping cart and accepting the order, they will be directed to the next page where anable add or select address and payment method. If they choose online, they will be redirected to the bank page, pay, and then deliver Page
</p>
<p align="center"><img src="https://user-images.githubusercontent.com/63088252/235297273-4a571b5d-d4ad-4db5-a4ad-1f3ae8baf027.jpg" />
  <img src="https://user-images.githubusercontent.com/63088252/235297285-51d89823-7eb7-44f2-bf6a-146ac3596341.jpg" /> <img src="https://user-images.githubusercontent.com/63088252/235297291-436f64b5-0a52-4dc2-aefa-0aeaba7adcc1.jpg" /> <br /> <img src="https://user-images.githubusercontent.com/63088252/235297292-646276b1-9e0b-4875-a2ec-211a6bb70679.jpg" /> <img src="https://user-images.githubusercontent.com/63088252/235297298-55bdfb93-3e63-4375-b0e2-b1805d5bddb6.jpg" /></p>
<br />


<br />
<p text-align: left> <b> Current & History Orders </b> <br /><br /> 
</p>
<p align="center"><img src="https://user-images.githubusercontent.com/63088252/235297405-3b05388a-eb1e-4df0-af7e-2de42c7d32c1.jpg" />
  <img src="https://user-images.githubusercontent.com/63088252/235297412-aaf99cec-4998-431a-9091-a8dac5cc1819.jpg" /> <img src="https://user-images.githubusercontent.com/63088252/235297415-6c4ba031-7926-4aef-8d96-c79357cf5bbd.jpg" /></p>
<br />

<br />
<p text-align: left> <b> Internet & Server Disconnection </b> <br /><br /> 
</p>
<p align="center"><img src="https://user-images.githubusercontent.com/63088252/235297556-dac07fb6-40df-4909-ada1-7bbd9a5b6226.jpg" />
  <img src="https://user-images.githubusercontent.com/63088252/235297563-646cbb16-5e02-4e3c-90a6-79d60cc381c7.jpg" /> </p>
<br />



