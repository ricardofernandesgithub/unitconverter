package pt.example.rf.conversorunidades;

/**
 * Created by ricardo fernandes on 28/05/2017.
 */

public class ConversoesClass {

    static private double resultado;

    static public double conversoes(String chamarMetodo, double valor) {

        switch (chamarMetodo) {

            //Temperatura
            case "0921":
                //"Fahrenheit - Celsius"
                resultado = (valor - 32) * 5 / 9;
                break;
            case "0901":
                //"Kelvin - Celsius"
                resultado = valor - 273.15;
                break;
            case "0912":
                //"Celsius - Fahrenheit"
                resultado = valor * 9 / 5 + 32;
                break;
            case "0910":
                //"Celsius - Kelvin"
                resultado = valor + 273.15;
                break;
            case "0902":
                //"Kelvin - Fahrenheit"
                resultado = valor * 9 / 5 - 459.67;
                break;
            case "0920":
                //"Fahrenheit - Kelvin"
                resultado = (valor + 459.67) * 5 / 9;
                break;

            //Massa/Peso
            case "0601":
                //libra_kilograma
                resultado = valor * 0.4536;
                break;
            case "0610":
                //kilograma_libra
                resultado = valor * 2.2045855;
                break;
            case "0602":
                //libra_newton
                resultado = valor * 0.224808942443;
                break;
            case "0620":
                //newton_libra
                resultado = valor * 4.4482216;
                break;
            case "0621":
                //newton_kilograma
                resultado = valor * 0.102;
                break;
            case "0612":
                //kilograma_newton
                resultado = valor * 9.8067;
                break;

            //Comprimento/Distância
            case "0303":
                //"Centimetro - Polegada"
                resultado = valor * 0.3937;
                break;
            case "0330":
                //"Polegada - Centimetro"
                resultado = valor * 2.54;
                break;
            case "0325":
                //"Kilometro - Milha"
                resultado = valor * 0.6214;
                break;
            case "0352":
                //"Milha - Kilometro"
                resultado = valor * 1.6093;
                break;
            case "0314":
                //"Metro - Jarda"
                resultado = valor * 1.0936;
                break;
            case "0341":
                //"Jarda - Metro"
                resultado = valor * 0.9144;
                break;
            case "0301":
                //"Centimetro - Metro"
                resultado = valor * 0.01;
                break;
            case "0302":
                //"Centimetro - Kilometro"
                resultado = valor * 0.00001;
                break;
            case "0304":
                //"Centimetro - Jarda"
                resultado = valor * 0.0109;
                break;
            case "0305":
                //"Centimetro - Milha"
                resultado = valor * 0.000006;
                break;
            case "0310":
                //"Metro - Centimetro"
                resultado = valor * 100;
                break;
            case "0312":
                //"Metro - Kilometro"
                resultado = valor * 0.001;
                break;
            case "0313":
                //"Metro - Polegada"
                resultado = valor * 39.3701;
                break;
            case "0315":
                //"Metro - Milha"
                resultado = valor * 0.0006;
                break;
            case "0331":
                //"Polegada - Metro"
                resultado = valor * 0.0254;
                break;
            case "0332":
                //"Polegada - Kilometro"
                resultado = valor * 0.00003;
                break;
            case "0334":
                //"Polegada - Jarda"
                resultado = valor * 0.0278;
                break;
            case "0335":
                //"Polegada - Milha"
                resultado = valor * 0.00002;
                break;
            case "0320":
                //"Kilometro - Centimetro"
                resultado = valor * 100000;
                break;
            case "0321":
                //"Kilometro - Metro"
                resultado = valor * 1000;
                break;
            case "0323":
                //"Kilometro - Polegada"
                resultado = valor * 39370.0787;
                break;
            case "0324":
                //"Kilometro - Jarda"
                resultado = valor * 1093.6133;
                break;
            case "0340":
                //"Jarda - Centimetro"
                resultado = valor * 91.44;
                break;
            case "0343":
                //"Jarda - Polegada"
                resultado = valor * 36;
                break;
            case "0342":
                //"Jarda - Kilometro"
                resultado = valor * 0.0009;
                break;
            case "0345":
                //"Jarda - Milha"
                resultado = valor * 0.0006;
                break;
            case "0350":
                //"Milha - Centimetro"
                resultado = valor * 160934.4;
                break;
            case "0351":
                //"Milha - Metro"
                resultado = valor * 1609.344;
                break;
            case "0353":
                //"Milha - Polegada"
                resultado = valor * 63360;
                break;
            case "0354":
                //"Milha - Jarda"
                resultado = valor * 1760;
                break;

            //Área
            case "0101":
                //"Hectare - Metro Quadrado"
                resultado = valor * 10000;
                break;
            case "0102":
                //"Hectare - Milha Quadrada"
                resultado = valor * 0.0039;
                break;
            case "0110":
                //"Metro Quadrado - Hectare"
                resultado = valor * 0.0001;
                break;
            case "0112":
                //"Metro Quadrado - Milha Quadrada"
                resultado = valor * 0.0000004;
                break;
            case "0120":
                //"Milha Quadrada - Hectare"
                resultado = valor * 258.9988;
                break;
            case "0121":
                ///"Milha Quadrada - Metro Quadrado"
                resultado = valor * 2589988.11;
                break;

            //Binário
            case "0201":
                //"Newton Metro - Libra Força Pé"
                resultado = valor * 0.7376;
                break;
            case "0210":
                //"Libra Força Pé - Newton Metro"
                resultado = valor * 1.3558;
                break;

            //Divisas
            case "0412":
                //"Euro - Dolar(USD)"
                resultado = valor * 1.14009;
                break;
            case "0410":
                //"Euro - Libra(GBP)"
                resultado = valor * 0.884595;
                break;
            case "0421":
                //"Dolar(USD) - Euro"
                resultado = valor * 0.877126;
                break;
            case "0420":
                //"Dolar(USD) - Libra(GBP)"
                resultado = valor * 0.775901;
                break;
            case "0401":
                //"Libra(GBP) - Euro"
                resultado = valor * 1.13046;
                break;
            case "0402":
                //"Libra(GBP) - Dolar(USD)"
                resultado = valor * 1.28882;

                //Energia
            case "0501":
                //"Joule - Caloria"
                resultado = valor * 0.2388;
                break;
            case "0502":
                //"Joule - BTU"
                resultado = valor * 0.0009;
                break;
            case "0510":
                //"Caloria - Joule"
                resultado = valor * 4.1868;
                break;
            case "0512":
                //"Caloria - BTU"
                resultado = valor * 0.004;
                break;
            case "0520":
                //"BTU - Joule"
                resultado = valor * 1055.06;
                break;
            case "0521":
                //"BTU - Caloria"
                resultado = valor * 251.9968;
                break;

            //Potência
            case "0701":
                //"Cavalo - Kilowatt"
                resultado = valor * 0.7457;
                break;
            case "0710":
                //"Kilowatt - Cavalo"
                resultado = valor * 1.341;
                break;

            //Pressão
            case "0801":
                //"Bar - PSI"
                resultado = valor * 14.5038;
                break;
            case "0810":
                //"PSI - Bar"
                resultado = valor * 0.0689;
                break;

            //Velocidade
            case "1001":
                //"Metro/Segundo - Kilometro/Hora"
                resultado = valor * 3.6;
                break;
            case "1010":
                //"Kilometro/Hora - Metro/Segundo"
                resultado = valor * 0.2778;
                break;

            //Volume
            case "1110":
                //"Galão(US) - Litro"
                resultado = valor * 3.7854;
                break;
            case "1101":
                //"Litro - Galão(US)"
                resultado = valor * 0.2642;
                break;
        }
        return resultado;
    }

}
