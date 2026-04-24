package src.estructuras.par;

import java.util.Objects;

public class Par<T, U> {

//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------

    private T valor1;
    private U valor2;

//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    public Par(T valor1, U valor2) {
        this.setValor1(valor1);
        this.setValor2(valor2);
    }

//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        Par<?, ?> other = (Par<?, ?>) obj;
        return Objects.equals(this.getValor1(), other.getValor1()) &&
                Objects.equals(this.getValor2(), other.getValor2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getValor1(), this.getValor2());
    }

    @Override
    public String toString() {
        return "(" + this.getValor1() + ", " + this.getValor2() + ")";
    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    public T getValor1() {
        return this.valor1;
    }

    public U getValor2() {
        return this.valor2;
    }

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------


    public void setValor1(T valor1) {
        this.valor1 = valor1;
    }

    public void setValor2(U valor2) {
        this.valor2 = valor2;
    }
}
