import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

public class MainJogo {
	
	public static DisplayMode monitor = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

	public static void main(String[] args) {
		
		JFrame janela = new JFrame("Sea Invaders");
		
		janela.setSize(1366, 768);
		//janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
		janela.setUndecorated(true);
		janela.setVisible(true);
		janela.setLayout(null);
		janela.setLocationRelativeTo(null);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SpaceInvaders invasaoAlien = new SpaceInvaders();
		invasaoAlien.setBounds(0, 0, monitor.getWidth(), monitor.getHeight());
		
		janela.add(invasaoAlien);	
		
		janela.addKeyListener(invasaoAlien);

	}
	
}
