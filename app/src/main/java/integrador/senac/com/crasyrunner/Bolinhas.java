package integrador.senac.com.crasyrunner;

/**
 * Created by Jefferson on 02/05/2016.
 */
public class Bolinhas extends ElementoTela {

    public Bolinhas(int nivel){
        super(nivel);
        posY = -(raio * 2);
        posX = geraPosicaoAleatoria();
    }

    public Bolinhas(Cor cor){
        super(cor);
        posY = -(raio * 2);
        posX = geraPosicaoAleatoria();
    }

    @Override
    public void update() {
        posY += getVelocidade();
    }


}
