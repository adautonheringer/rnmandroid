# Rick And Morty #
This is an Android project to demonstrate architectural knowledge. It has two screens: A, a list of characters and B, a page with details. Here follows some screenshots. It is organized with packaged by features so you can navigate throw and understand the code.

![image](https://user-images.githubusercontent.com/30607119/215357590-a74bb06c-433a-409e-807c-09091290875f.png)
![image](https://user-images.githubusercontent.com/30607119/215358762-66c309b3-869d-4243-b57f-9712517cf85c.png)
![image](https://user-images.githubusercontent.com/30607119/215357609-50bd2ecd-e779-43b0-bc27-005a64de0969.png)

### main architecture  ###
It uses MVVM pattern and Kotlin Flows and Coroutines for unidirectional data flow. The layers are:
#### UI -> ViewModel -> Repository -> ApiServices ####
Also, it uses Kotlin, Retrofit, Glide, Hilt for dependency Injection, and Jetpack components such as navigation and data binding.

### tests ###

We test the viewModel for unit tests. We test if the chain of states is correct given an user action.

Build an Android project with the given public API below

### animation ###

It is good to have some choreography, so I encourage to download and compile the app to see how it works using the material design guidelines for the views and transitions between screens.

### next steps ###

There is no use of database in this project. A future work could be done using Room Database to demonstrate skills in Android data persistence.
