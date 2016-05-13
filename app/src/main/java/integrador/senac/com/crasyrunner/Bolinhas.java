package integrador.senac.com.crasyrunner;

/**
 * Created by Jefferson on 02/05/2016.
 */
public class Bolinhas extends ElementoTela {

    public Bolinhas(){
        super();
        posY = -(raio * 2);
        posX = geraPosicaoAleatoria();
    }

    public Bolinhas(Object[] obj){
        super(obj);
        posY = -(raio * 2);
        posX = geraPosicaoAleatoria();
    }

    @Override
    public void update() {
        posY += velocidade;
    }


}
