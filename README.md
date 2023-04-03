# Spring Security Example
Example of using spring security to add user/password to control access.

This is a simple Spring MVC app with Spring Security to control parts of the app with roles.

I started with this Spring Guide: https://spring.io/guides/gs/securing-web/

To run, localhost:8080

As you can see in the MVCController, besides the home page, I set up a page that needs login (/hello). The custom login page (/login) and a manager only page (where a user role needs to be "MANAGER")

There are different configuration for Basic Auth, default login form, custom login form. 
Configurations are set in this method, in a file that has @Configuration and @EnableWebSecurity
<p/>
<code>@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)</code>	
<p/>    
In this sample app I have provided several ways to specify what users and passwords to secure web pages.
<p/>
But only one way can be used at a time, to use one, make sure @Configuration is @EnableWebSecurity is on (and other ways are commented)

<b>Basic Auth</b><p/>
<img src="screenshot/basicauth.png" width="200"/>
<p/>
<b>Default login form</b><p/>
<img src="screenshot/defaultlogin.png" width="200"/>
<p/>
<b>Custom login form</b><p/>
<img src="screenshot/customlogin.png" width="200"/>
