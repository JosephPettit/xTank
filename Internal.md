# How to add Swing event items

***

## Example adding ActionListener /  Key Listener

### Adding ActionListener Example

#### Add ActionListener to SandboxFamePanel timer

Add ActionListener to intended object.
Add method for ActionListener to be added by Controller.

```java
class SandBoxGamePanel{

    public void addGameTimerListener(ActionListener actionListener){
        timer.addActionListener(actionListener);
    }
}
```

ActionListener is passed through View-Frame and to object

```java
class ClientFrame{
    SandBoxGamePanel gamePanel = new SandBoxGamePanel();

    public void addGamePanelTimerListener(ActionListener actionListener){
        gamePanel.addGameTimerListener(actionListener);
    }
}
```

When Controller is initialized, action listener is initialized and passed through frame and to intended object for listening.

All calculations (work) is then done by the model.

```java
class ClientController{
    ClientFrame clientFrame = new ClientFame();
    ClientModel clientModel = new ClientModel();

    setupActionListeners(){
        addGameTimerListener();
    }

    private void addGameTimerListener() {
        clientFrame.addGamePanelTimerListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientModel.moveTank();
            clientModel.checkCollision();
            clientFrame.repaint();
            }
        });
    }
}
