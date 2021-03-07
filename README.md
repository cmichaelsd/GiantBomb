# GiantBomb
This repo is for the Giant Bomb API. I have two fragments: the first is a render of a list of video games from a search query, the second is the individual details of a selected video game from the rendred list.

### Architecure
The application was created following MVVM architecture.

### Technologies Used
- Navigation Graph to navigation between fragments
- Retrofit for API calls
- Moshi to parse JSON data
- Glide to render images from URL
- Data Binding to render data direction to a fragment from a View-Model
- Coroutines to manage threads and debounce logic for API calls as query text is updated

### How To Run
- I have a Global.kt file within '/app/src/main/java/com/colemichaels/giantbomb' please ensure that the API_KEY is assigned to an API key for Giant Bomb. I will not upload an API key to Github. This must be manually done for each user of the repo.
- Once the API key is set run as you would any other pre-build Android application on an emulator or on a physical device.
