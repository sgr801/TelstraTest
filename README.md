# README #

### Summary ###

* __What was my apporach?__
    - Architecture  
    For This project I choose to go with MVVM architecture. There are a lot of advantages of using this architecture like `ViewModel` survives orientation changes out of the box, the architecture follows SOLID principles and splits reponsibilites to many smaller, maintainable components.

    - Android Jetpack  
    I added Room database. After every successful API call I store contents to DB so when Internet is not available it can at least retrieve data from its DB and show to the user.

* __What libraries did I use and why?__
    - Stetho (https://facebook.github.io/stetho/)  
    Stetho is a sophisticated debug bridge for Android applications. When enabled, developers have access to the Chrome Developer Tools feature natively part of the Chrome desktop browser ( Type "chrome://inspect/#devices" in browser). Where dev can easily monitor Database, storage, network calls etc.
    
    - Volley (https://github.com/google/volley)  
    Easy to set up, fast and recommended by Googl in their official documentation.

    - Glide (https://github.com/bumptech/glide)  
    One of the better performing image libraries. Supports lazy loading out of the box. Larger than Picasso, but faster.
    
    - Gson (https://github.com/google/gson)  
    Used for JSON parsing
    
    - Anko (https://github.com/Kotlin/anko)  
    Anko is a Kotlin library which makes Android application development faster and easier. In this project I used anko for async task
   
* __What would I do if I had more time?__
    - Improve the UI more.
    
### Branches ###
    - master
    - develop

### Who do I talk to? ###

* Repo owner or admin  

  Shekh Shagar  
  shagar.shekh@gamil.com

  [LinkedIn](https://www.linkedin.com/in/shekh-shagar-0513b184/) 
