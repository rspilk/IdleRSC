package listeners;

import bot.Main;
import controller.Controller;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * WindowListener is a listener which updates other windows based on what has been clicked on the
 * side panel.
 *
 * <p>WindowListener is always running, and it runs as a separate thread from the main bot.
 *
 * @author Dvorak
 */
public class WindowListener implements Runnable {

  final JFrame rscFrame;
  final JFrame botFrame;
  final JFrame consoleFrame;
  JScrollPane scroller;
  final JTextArea logArea;
  final Controller controller;

  public WindowListener(
      JFrame _botFrame,
      JFrame _consoleFrame,
      JFrame _rscFrame,
      JScrollPane _scroller,
      JTextArea _logArea,
      Controller _controller) {
    rscFrame = _rscFrame;
    botFrame = _botFrame;
    consoleFrame = _consoleFrame;
    scroller = scroller;
    logArea = _logArea;
    controller = _controller;
  }

  @Override
  public void run() {
    boolean consolePrevious = Main.isLogWindowOpen();

    while (true) {
      if (consolePrevious != Main.isLogWindowOpen()) {
        consoleFrame.setVisible(Main.isLogWindowOpen());
        consolePrevious = Main.isLogWindowOpen();
      }

      if (Main.isSticky()) {
        if (consoleFrame.isVisible()) {
          if (!consoleFrame.getSize().equals(new Dimension(rscFrame.getWidth(), 225))) {
            consoleFrame.setSize(rscFrame.getWidth(), 225);
          }
          if (!consoleFrame
              .getLocation()
              .equals(
                  new Point(
                      rscFrame.getLocation().x, rscFrame.getLocation().y + rscFrame.getHeight()))) {
            consoleFrame.setLocation(
                rscFrame.getLocation().x, rscFrame.getLocation().y + rscFrame.getHeight());
          }
        }
        if (botFrame.isVisible()) {
          if (!botFrame
              .getSize()
              .equals(new Dimension(botFrame.getWidth(), rscFrame.getHeight()))) {
            botFrame.setSize(botFrame.getWidth(), rscFrame.getHeight());
          }
          if (!botFrame
              .getLocation()
              .equals(
                  new Point(
                      rscFrame.getLocation().x + rscFrame.getWidth(), rscFrame.getLocation().y))) {
            botFrame.setLocation(
                rscFrame.getLocation().x + rscFrame.getWidth(), rscFrame.getLocation().y);
          }
        }
      }

      try {
        Thread.sleep(40);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
