import java.awt.Color;
import java.awt.Graphics2D;


public class TiroBoss extends Tiro{

	public TiroBoss(int inicioX, int inicioY) {
		super(inicioX, inicioY);
	}


	public boolean colideCom(Nave nave) {
			if (this.y >= nave.getY() && this.x==nave.getX()) {
				return true;
			}else{
				return false;
			}
	}
}
