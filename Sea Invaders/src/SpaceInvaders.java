import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SpaceInvaders extends JPanel implements Runnable, KeyListener {

	/**
	 * 
	 */
	 private int b;
	 private int a;
	private static final long serialVersionUID = 1L;
	private Nave nave;
	private int direcao = 0;
	private ArrayList<Tiro> tiros;
	private Cabeca1 cabeca1;
	private ArrayList<Inimigo> inimigos;
	private ArrayList<Explosao> explosoes;
	private PlanoDeFundo fundo;
	private boolean ganhou;
	private boolean perdeu;
	private Font minhaFonte = new Font("Consolas", Font.BOLD, 30);
	private BufferedImage imagemExplosao;
	private BufferedImage imagemBoss;
	private int score;
	private int ponto;
	
	//CONSTRUTOR
	public SpaceInvaders() {
		a=1;
		b=0;
		nave = new Nave();
		
		tiros = new ArrayList<Tiro>();
		
		inimigos = new ArrayList<Inimigo>();
		
		explosoes = new ArrayList<Explosao>();
		
		fundo = new PlanoDeFundo();
		cabeca1=null;
		ganhou = false;
		perdeu = false;
		
		ponto = 100;
		score = ponto - 100;
		
		//CRIA OS INIMIGOS
		BufferedImage imagemInimigo = null;
		try {
			imagemInimigo = ImageIO.read(new File("imagens/inimigo.png"));
			imagemExplosao = ImageIO.read(new File("imagens/explosao.png"));
			imagemBoss = ImageIO.read(new File("imagens/bossAzul.png"));
		} catch(IOException e) {
			System.out.println("Nao foi possivel carregar imagem inimigo");
			e.printStackTrace();
		}
		
		
		for(int i = 0; i < 30; i++) {
			if ( i%2 == 0)
				inimigos.add(new Inimigo(imagemInimigo, 1150 + i/5 * 120, 20 + i%5 * 60, 1));
			//if ( i%2 != 0)
				//inimigos.add(new Inimigo(imagemInimigo, 1300 + i/10 * 60, 80 + i%10 * 60, 1));

		}
		
		Thread lacoDoJogo = new Thread(this);
		lacoDoJogo.start();
		
	}

	@Override
	public void run() {
		
		while (true) {
			
			long tempoInicial = System.currentTimeMillis();
			
			update();
			repaint();
			
			long tempoFinal = System.currentTimeMillis();
			
			long diferenca = 16 - (tempoFinal - tempoInicial);
			
			if (diferenca > 0)
				dorme(diferenca);
			
			//System.out.println("rodou em: " + (tempoFinal - tempoInicial));
		}	
		
	}
	
	private void update() {
		
		if(perdeu == false) {
			if(inimigos.size() == 0) {
					
					if(a==1){
					ganhou = true;
					cabeca1 = new Cabeca1(imagemBoss, 750, 75, 1);
					a++;
					}

			}
		}
		
		//UPDATE NAVE
		nave.movimento(direcao);
		
		if(cabeca1!=null){
		cabeca1.atualizar();
		}
		
		//UPDATE INIMIGOS
		for(int i = 0; i < inimigos.size(); i++) {
			inimigos.get(i).atualizar();
			
			if(inimigos.get(i).getX() <= 250) {
				perdeu = true;
			}
		}
		
		for(int i = 0; i < inimigos.size(); i++) {
			if(inimigos.get(i).getY() <= 0 || inimigos.get(i).getY() >= 768 - 80) {
				ponto+=50;
				for(int j = 0; j < inimigos.size(); j++) {
					inimigos.get(j).trocaDirecao();
				}
				break;
			}
		}
		if(ganhou==true){
			if(cabeca1.getY()<=0||cabeca1.getY()>=768-90) {
					cabeca1.trocaDirecao();
			}
		}
		//UPDATE EXPLOSAO
		for(int i = 0; i < explosoes.size(); i++) {
			explosoes.get(i).atualizar();
			
			if(explosoes.get(i).acabou()) {
				explosoes.remove(i);
				i--;
			}
		}
		//UPDATE TIROS
		for(int i = 0; i < tiros.size(); i++) {
			tiros.get(i).atualiza();
			
			if (tiros.get(i).destroy()) {
				tiros.remove(i);
				i--;
			}else {
				for(int j = 0; j < inimigos.size(); j++) {
					if(tiros.get(i).colideCom(inimigos.get(j))) {
					
						explosoes.add(new Explosao(imagemExplosao, inimigos.get(j).getX(), inimigos.get(j).getY()));
						try {
							MakeSound.play("explosao.wav");
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						inimigos.remove(j);
						score += ponto;
						j--;
						tiros.remove(i);
					
						break;
					}
				}
					if(ganhou==true){
						if(tiros.get(i).colideCom(cabeca1)){
							b++;
							explosoes.add(new Explosao(imagemExplosao, (int)cabeca1.getX(), (int)cabeca1.getY()+50));
							try {
								MakeSound.play("explosao.wav");
							} catch (Exception e) {
								e.printStackTrace();
							}
							if(b==5){
							cabeca1=null;
							ganhou=false;
							}
							score += 500;
							tiros.remove(i);
					
							break;
						}
					}
			}
		}
		
		
	}

	public void paintComponent(Graphics g2) {
		
		super.paintComponent(g2);  //limpar a tela
		
		Graphics2D g = (Graphics2D) g2.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		fundo.pinta(g);
		
		//DESENHA SCORE
		g.drawString("Score: " + score, 20, 20);
		
		//DESENHA EXPLOSOES
		for(int i = 0; i < explosoes.size(); i++) {
			explosoes.get(i).pintar(g);
		}
		
		//DESENHA INIMIGOS
		for(int i = 0; i < inimigos.size(); i++) {
			inimigos.get(i).pintar(g);
		}
		
		//DESENHA NAVE
		nave.pinta(g);
		
		//DESENHA BOSS
		if(ganhou==true){
		cabeca1.pintar(g);
		}
		
		//DESENHA TIROS
		for(int i = 0; i < tiros.size(); i++) {
			tiros.get(i).pintar(g);
		}
		
		//DESENHA GANHAR
		//if(ganhou) {
			//g.setColor(Color.white);
			//g.setFont(minhaFonte);
			//g.drawString("VOCE TERMINOU O JOGO!!!", 480, 384);

		//}
		//DESENHA PERDEU
		if(perdeu) {
			g.setColor(Color.white);
			g.setFont(minhaFonte);
			g.drawString("VOCE PERDEU!!!", 520, 384);
			inimigos.removeAll(inimigos);

		}
		
	}
	
	private void dorme(long duracao) {
		
		try {
			Thread.sleep(duracao);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			direcao = 1;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			direcao = -1;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE && nave.podeAtirar()) {
			tiros.add(nave.atirar());
			try {
				MakeSound.play("tiro.wav");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			direcao = 0;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			direcao = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
		
	}
}

