# Randomizer

Webapp using `Spring MVC` and `Angular 5` for randomizing player names!

[random]: screenshot/random.png "Randomize in action!"
[edit]: screenshot/edit.png "Edit option"
[home]: screenshot/home.png "Home view"
[create]: screenshot/create.png "Create a player"
[logo]: frontend/src/favicon.ico

![Randomize logo][logo]

------

In order to work on the `frontend` you must run the following commands:

```sh
$ cd path/to/project/frontend
$ npm install -g @angular/cli@1.7.0-beta.1
$ npm install --save-dev @angular/cli@1.7.0-beta.1
```

Once you have the environment setup, you must run the `backend` application on a server. Import the application into your preferred IDE and run it! _(I used NetBeans 8.2)_

After you're done running `backend`, head to:

```sh
$ cd path/to/project/frontend
$ cd src/app/services/
```

And change the value of **RANDOMIZER_API_URL** to your `backend` URL.

Now run your `frontend` app with the following command:

```sh
$ cd path/to/project/frontend
$ ng serve --open
```

Then head to  `http://localhost:4200/` to see the application running!


![Home view][home]

## _Create_ a player

To create a new player, type the new name on the top right textbox and then click create, as the following image indicates

![Create a player][create]

**Note:** If you want to change pre-loaded names go to:

```sh
$ cd path/to/project/backend/src/main/resources/
```

Edit the file `players.txt` and load your default names!

## _Edit_ a player

Once you click on a list item, you'll see this

![Edit a player][edit]

## _Random_ function

To _randomize_ click on _**Randomize**_ label on the top left of the screen and see the magic!

![Randomize][random]

------

# Deploying as one application on a server

If you intent to run it as a single application on a server you must do the following:

```sh
$ cd path/to/project/frontend
$ ng build --base-href /co.com.randomizer/Angular/ --prod
```

This will create a _**dist**_ folder inside your `frontend` folder. Copy **ALL** of the content and head to:

```sh
$ cd path/to/project/backend
$ cd co.com.randomizer/src/main/webapp/Angular
```

And replace every file in that folder with the new ones.

Now just re-compile the `backend` and deploy as one!
