import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;
import com.googlecode.lanterna.screen.Screen;
import java.util.ArrayList;

public class Field{
  private ArrayList<Floor> floor;
  public Field(){
    floor = new ArrayList<Floor>();
    Floor levelOne = new Floor(1);
    levelOne.addWall(0,0);
    levelOne.addWall(10,10);
    floor.add(levelOne);
  }
  public static void main(String[] args) {
    Player bob = new Player(100, 10, 10, 2);
    Terminal terminal = TerminalFacade.createTextTerminal();
    Screen screen = new Screen(terminal);
    Field playingField = new Field();
    screen.startScreen();
    screen.putString(1,3,"Health: " + bob.getHealth(), Terminal.Color.WHITE,Terminal.Color.RED);
    for (int floorLevel = 0; floorLevel < playingField.floor.size(); floorLevel++){
      Floor current = playingField.floor.get(floorLevel);
      for (int currentWall = 0; currentWall < current.getBorder().size(); currentWall++){
        terminal.moveCursor(current.getBorder().get(currentWall).getX(),current.getBorder().get(currentWall).getY());
        terminal.putCharacter(current.getBorder().get(currentWall).getLogo());
      }
    }
    screen.refresh();
    boolean running = true;
    while (running){
      terminal.moveCursor(bob.getX(),bob.getY());
      terminal.putCharacter(bob.getCharacter());
      Key key = terminal.readInput();
      terminal.setCursorVisible(false);
      if (key != null){
        if (key.getKind() == Key.Kind.Escape){
          screen.stopScreen();
          running = false;
        }
        if (key.getKind() == Key.Kind.ArrowUp){
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(' ');
          bob.move("up");
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(bob.getCharacter());
        }
        if (key.getKind() == Key.Kind.ArrowDown){
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(' ');
          bob.move("down");
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(bob.getCharacter());
        }
        if (key.getKind() == Key.Kind.ArrowLeft){
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(' ');
          bob.move("left");
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(bob.getCharacter());
        }
        if (key.getKind() == Key.Kind.ArrowRight){
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(' ');
          bob.move("right");
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(bob.getCharacter());
        }
      }
    }
  }
}
