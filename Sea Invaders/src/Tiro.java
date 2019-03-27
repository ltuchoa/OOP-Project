
import java.awt.Color;
import java.awt.Graphics2D;

public class Tiro {

	protected int x, y;
	private int velocidade;
	private int tamX = 16;
	private int tamY = 4;
	
	public Tiro(int inicioX, int inicioY) {
		
		this.x = inicioX;
		this.y = inicioY;
		velocidade = 10;
	}
	
	
	public void pintar(Graphics2D g) {
		
		g.setColor(Color.yellow);
		g.fillRect(x, y, tamX, tamY);
		
	}


	public void atualiza() {
		x += velocidade;
		
	}
	
	public boolean destroy() {
		
		return x > 1360;
	}


	public boolean colideCom(Inimigo inimigo) {
		if(x >= inimigo.getX() && x + tamX <= inimigo.getX() + inimigo.getTam()) {
			if (y >= inimigo.getY() && y + tamY <= inimigo.getY() + inimigo.getTam()) {
				return true;
			}
		}
		
		return false;
	}
	public boolean colideCom(Cabeca1 cabeca1) {
		if(x >= cabeca1.getX() && x + tamX <= cabeca1.getX() + 30){
			if (y >= cabeca1.getY() && y + tamY <= cabeca1.getY()+300) {
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
