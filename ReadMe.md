# X-Tank information

## How To Play
### Run ServerMain.java

![image](https://user-images.githubusercontent.com/32590397/201498782-1faa501e-c07a-4e31-9f9c-43a21bf4efe4.png)

Run ServerMain.java and select which map you would like to use. Then select how many players (2-4).
The server will start with the selected settings and wait for each player to join.

### Run ClientMain.java

![image](https://user-images.githubusercontent.com/32590397/201498846-946aead0-a8c7-4b2b-9105-ea89d50b6708.png)

Run ClientMain.java and enter the IP address of the server ("localhost" if using the same computer as server).
Each player will need to spin up and instance of ClientMain.java before the game will begin.

### Playing the Game

![image](https://user-images.githubusercontent.com/32590397/201501461-b4f9c80c-266f-445f-8c1d-b81c525f66cd.png)

You will spawn in as the tank color you selected, this should help you keep track of who is who. Shoot the other tanks until they die!

## Controls
Up/Down: Move the tank forward and backward
Left/Right: Rotate the tank on its axis
Space: Fire a missile

## Server

***

## Run Server

- run ServerMain.java

## Server MVC layout

### Server Model

- ServerModel.java

### Server View

- ServerFrame.java (main window)
  - ServerCardPanel.java (Holds views)
    - ServerSettingsPanel.java -> welcome screen.
    - ServerRunningPanel.java -> server running screen.

### Server Controller

- ServerController.java

***

</br>

## Client

***

## Run Client

- run ClientMain.java

## Client MVC layout

### Client Model

- ClientModel.java

### Client View

- ServerFrame.java (main window)
  - ClientCardPanel.java (Holds views)
    - ClientConnectPanel.java -> welcome screen. (need to add).
    - SandboxGamePanel.java -> sandbox game mode -> MVP from what John created.

### Client Controller

- ClientController.java

***
