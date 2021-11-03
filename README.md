# Application: Veli App by Ariadna Aldeguer
This is the first part delivery of the "Interface Development" class. 

The application allows you to save travel destinations. 

The theme chosen are taken from a sunset on the beach, the memory of a good holiday. 

The name of the application is extracted from the word tra**veli**ng.


## What does first delivery do? 
The delivery has two parts:
- M8. Mobile Application Development: 
  -  Login working correctly 
  -  Bottom navigation using interaction with fragments
  -  A database has been set up to store the information, we can delete one item or delete all
  -  A screen with a form to add data on database
  -  A screen with a recyclerview to list the data from database
  -  Information toast has been implented
-  M7: User Interface Design and Implementation
    -  A splash screen when the app is loading
    -  Created a logo for the app and the pattern style has been defined
    -  Login and form screen design 

## Screenshots 
#### Splash Screen
A standby screen while the application is loading.

![Splash_Screen](https://user-images.githubusercontent.com/71792438/140075878-b8dd6cdb-d59e-42a3-8d8a-ee39eacc7391.png)


#### Login Screen
Retrieve from the user the credentials to get into the application. If access is denied, displays an information message.

![Login_Screen](https://user-images.githubusercontent.com/71792438/140072995-4371245b-f03c-474a-880f-b551f33546e9.png)

#### Home Screen
It is the distributor of the application, it shows a summary of important information.

![Home_Screen](https://user-images.githubusercontent.com/71792438/140073416-9eca5dea-99c4-4937-bc05-5fb69be91581.png)

#### Form Screen
Contains a form to add information to the database. Create a "Travel" with country, city and airport properties. 
The application is only saving the travel if it have at least one property, otherwise a information message appears.

![Form_Screen](https://user-images.githubusercontent.com/71792438/140073901-82cd417c-aeb0-4e77-8b71-45202616ca49.png)

#### List Screen
Display list of travels using a recycler view and grabbing data from the database.
We can delete one item or delete them all. The latter asks for confirmation to proceed.

![List_Screen](https://user-images.githubusercontent.com/71792438/140078968-f5653530-ecf4-4449-8b5b-cb65abaa5ed9.png)


#### Settings Screen
Basic application settings, such as language or day/night theme.

![Settings_Screen](https://user-images.githubusercontent.com/71792438/140075510-b8e00c20-9c96-449b-8a95-9bfc8635deb8.png)

## Technologies
This project is developed with android studio (java).
I've used this extra clases:
* Activitys to provides the window in which the app draws its UI.
* Fragment Manager that represents a reusable portion of the app's UI. (Includes Home, Form, List and Settings screens)
* TravelsContract to store in constants variables the database properties.
* TravelsDBHelper extended SQLiteOpenHelper which helps to manage database creation and version management.
* Travel Class to estructure the concept of Travel.
* ReciclyerViewAdapter which it's a container used for displaying large amount of data sets that can be scrolled very efficiently.
* Toast to  provides simple feedback about an operation in a small popup. 
* A ScrollView to make vertically scrollable views.
* A Bottom navigation to switch easly between fragments screen.
* A top app bar for future implementation. 


## Code structure
- `AndroidManifest:` Describes essential information for Android building.
- `Res:` Includes the resources that Android Application uses:
    - `Drawable:` Includes the icons in the application.
    - `Layout:` The archive 'Activity_Main' has the design of the Login Screen.
    - `Values:` Contains the archive 'Color' with the primary colors of the app.

## App access
- To access the application the credentials are:
  - **User**: 123
  - **Password**: 123
- The applications has a **runnable APK**: 'app-debug'

## Extra information
#### Design 
Color palette 

![Theme](https://user-images.githubusercontent.com/71792438/140078233-a80492fc-ed2b-4982-9d4f-ff8db8a15579.png)

Font Roboto

![Roboto](https://user-images.githubusercontent.com/71792438/140086000-a67aeb07-a5ec-4be5-aa74-7f679e33c247.png)


## Extra tasks
* ScrollView on login screen
* Possibility of deleting an item

## Unfinished Tasks
- [ ] Implementation of editing an item
- [ ] Scroll view on form screen
- [ ] Top app bar 


