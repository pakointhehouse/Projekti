package Glavna;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Glavna {

	
	public static List <String> zaustavneRijeci = new ArrayList<String>();
	public static List <String> rijeciTekst1 = new ArrayList<String>();
	public static List <String> rijeciTekst2 = new ArrayList<String>();
	public static Vector <ElementVektora> vektor1 = new Vector<ElementVektora>();
	public static Vector <ElementVektora> vektor2 = new Vector<ElementVektora>();
	public static Vector <ElementVektora> usporedniVektor1 = new Vector<ElementVektora>();
	public static Vector <ElementVektora> usporedniVektor2 = new Vector<ElementVektora>();
	
	
	public static void main(String args[]) {
		
		Path stopWords = Path.of("stopWords.txt");
		Path tekst1 = Path.of("tekst1.txt");
		Path tekst2 = Path.of("tekst2.txt");
		String citajRijeci = null;
		String citajTekst1 = null;
		String citajTekst2 = null;
		
		try {
			citajRijeci = Files.readString(stopWords);
			citajTekst1 = Files.readString(tekst1);
			citajTekst2 = Files.readString(tekst2);
			
		}catch(IOException e) {
			e.printStackTrace();
		}

		
		String pomRijeci[] = citajRijeci.split("\\r?\\n");
		String pomTekst1[] = citajTekst1.split("[\\p{Punct}\\s]+");
		String pomTekst2[] = citajTekst2.split("[\\p{Punct}\\s]+");
		
		
		
		for (int i = 0;i<pomRijeci.length;i++) {
			zaustavneRijeci.add(pomRijeci[i]);
		}
		
		for(int i= 0 ;i<pomTekst1.length;i++) {
			rijeciTekst1.add(pomTekst1[i]);
		}
		
		for (int i=0 ;i<pomTekst2.length;i++) {
			rijeciTekst2.add(pomTekst2[i]);
		}
		
		rijeciTekst1.replaceAll(String::toLowerCase);
		rijeciTekst2.replaceAll(String::toLowerCase);
			
		for (int i = 0 ;i <zaustavneRijeci.size();i++) {
			for (int j = 0;j<rijeciTekst1.size();j++) {
				if(zaustavneRijeci.get(i).equals(rijeciTekst1.get(j))) {
					rijeciTekst1.remove(j);
				}
			}
		}
		
		for (int i = 0 ;i <zaustavneRijeci.size();i++) {
			for (int j = 0;j<rijeciTekst2.size();j++) {
				if(zaustavneRijeci.get(i).equals(rijeciTekst2.get(j))) {
					rijeciTekst2.remove(j);
				}
			}
		}
		
		ispisiTekstove(citajRijeci,citajTekst1,citajTekst2);
		ispisiKorigiraneTekstove(rijeciTekst1,rijeciTekst2);
		glavnaVektor1();
		glavnaVektor2();
		ispisiVektorRijeci();
		dodajUsporedniVektor1();
		dodajUsporedniVektor2();
		sumaKvadrataVektora();
		udaljenostKosinusaVektora();
		jacardovIndex();
		
		
	
	}
	
	
	
	
	//dodaje rijeci i frekvencije u vektor1
	private static void glavnaVektor1() {
		for(int i = 0; i < rijeciTekst1.size();i++) {
			Integer odabir = provjeriPostojanost(rijeciTekst1,i);
			if(odabir==0) {
				vektor1.add(unesiNoviVektor(rijeciTekst1,i));
			}else {
				vektor1.get(odabir).setFrekvencija(vektor1.get(odabir).getFrekvencija()+1);
			}
		}
	}
	
	//dodaje rijeci i frekvencije u vektor2
	private static void glavnaVektor2() {
		for(int i = 0; i < rijeciTekst2.size();i++) {
			Integer odabir = provjeriPostojanost(rijeciTekst2,i);
			if(odabir==0) {
				vektor2.add(unesiNoviVektor(rijeciTekst2,i));
			}else {
				vektor2.get(odabir).setFrekvencija(vektor2.get(odabir).getFrekvencija()+1);
			}
		}
	}
	
	
	//Provjerava postoji li već odredena rijec
	private static Integer provjeriPostojanost(List<String> lista,Integer index) {
		Integer pomInt=0;
		for (int i = 0;i<index;i++) {
			if(lista.get(index).equals(lista.get(i))) {
				return pomInt = i;
				
			}
		}
			return pomInt;
	}
	
	
	private static ElementVektora unesiNoviVektor(List<String> lista,Integer index) {
		return new ElementVektora(1,lista.get(index));
	}
	
	//napravi paralelni Vektor1 za usporedivanje
	private static void dodajUsporedniVektor1() {
		
		for (int i =0;i<vektor1.size();i++) {
			usporedniVektor1.add(vektor1.get(i));
		}
		
		for (int i =0;i<vektor2.size();i++) {
			ElementVektora elem = new ElementVektora(0,vektor2.get(i).getRijec());
			usporedniVektor1.add(elem);	
		}
		
		for (int i = 0;i<usporedniVektor1.size();i++) {
			for (int j = 0;j<i;j++) {
				if(usporedniVektor1.get(i).getRijec().equals(usporedniVektor1.get(j).getRijec()) &&(usporedniVektor1.get(i).getFrekvencija()!=usporedniVektor1.get(j).getFrekvencija()) ) {
					usporedniVektor1.remove(i);
				}
			}
		}
	}
	
	//napravi paralelni Vektor2 za usporedivanje
	private static void dodajUsporedniVektor2() {
		for (int i =0;i<vektor1.size();i++) {
			ElementVektora elem = new ElementVektora(0,vektor1.get(i).getRijec());
			usporedniVektor2.add(elem);	
		}
		
		for (int i =0;i<vektor2.size();i++) {
			usporedniVektor2.add(vektor2.get(i));
		}
		
		for (int i = 0;i<usporedniVektor2.size();i++) {
			for (int j = 0;j<i;j++) {
				if(usporedniVektor2.get(i).getRijec().equals(usporedniVektor2.get(j).getRijec()) &&(usporedniVektor2.get(i).getFrekvencija()!=usporedniVektor2.get(j).getFrekvencija()) ) {
					usporedniVektor2.get(j).setFrekvencija(1);
					usporedniVektor2.remove(i);
				}
			}
		}
	}
	
	private static void sumaKvadrataVektora() {
		Integer pomInt = 0;
		Integer reza= 0;
		for (int i = 0 ;i<usporedniVektor1.size();i++) {
			pomInt = (usporedniVektor1.get(i).getFrekvencija()-usporedniVektor2.get(i).getFrekvencija());
			pomInt = pomInt*pomInt;
			reza = reza+pomInt;
			
		}
		System.out.println("Rezultat mjere sume kvadrata udaljenosti jest: "+reza);
	}
	
	private static void udaljenostKosinusaVektora() {
		double a = 0;
		double b = 0;
		double c = 0;
		double total;
		for (int i = 0 ;i<usporedniVektor1.size();i++) {
			a=a+(usporedniVektor1.get(i).getFrekvencija()*usporedniVektor2.get(i).getFrekvencija());
			b=b+(usporedniVektor1.get(i).getFrekvencija()*usporedniVektor1.get(i).getFrekvencija());
			c=c+(usporedniVektor2.get(i).getFrekvencija()*usporedniVektor2.get(i).getFrekvencija());
		}
		b=Math.sqrt(b);
		c=Math.sqrt(c);
		total = a/(b*c);
		System.out.println("Udaljenost kosinusa vektora jest: "+total);
	}
	
	private static void jacardovIndex() {
		 List <String> zajednickeRijeci = new ArrayList<String>();
		double jacardov;
		double x;
		double y;
		
		for (int i = 0;i<usporedniVektor1.size();i++) {
			if(usporedniVektor1.get(i).getRijec().equals(usporedniVektor2.get(i).getRijec()) && usporedniVektor1.get(i).getFrekvencija()==usporedniVektor2.get(i).getFrekvencija()) {
				zajednickeRijeci.add(usporedniVektor1.get(i).getRijec());
			}
		}
	x = zajednickeRijeci.size();
	y= (vektor1.size()+vektor2.size()-zajednickeRijeci.size());
	jacardov = x/y;
	System.out.println("Rezultat mjere jacardovog indexa jest: "+jacardov);
	}
	
	private static void ispisiTekstove(String citajRijeci,String citajTekst1,String citajTekst2) {
		System.out.println("Zaustavne rijeci: ");
		System.out.println(citajRijeci);
		System.out.println("Prvi tekst: ");
		System.out.println(citajTekst1);
		System.out.println("Drugi tekst: ");
		System.out.println(citajTekst2);
	}
	
	private static void ispisiKorigiraneTekstove(List <String> rijeciTekst1,List <String> rijeciTekst2) {
		System.out.println("Korigirane rijeci prvog teksta: ");
		for (int i = 0;i<rijeciTekst1.size();i++) {
			System.out.println(rijeciTekst1.get(i));
		}
		System.out.println("Korigirane rijeci drugog teksta: ");
		for (int i = 0;i<rijeciTekst2.size();i++) {
			System.out.println(rijeciTekst2.get(i));
		}
	}
	
	private static void ispisiVektorRijeci() {
		
		
		System.out.println("Podaci vektora 1 :");
		for (int i = 0;i < vektor1.size();i++) {
			System.out.println("Rijec: "+vektor1.get(i).getRijec()+". Frekvencija: "+vektor1.get(i).getFrekvencija()+"");
		}
		System.out.println("\n");
		System.out.println("Podaci vektora 2 :");
		for (int i = 0;i < vektor2.size();i++) {
			System.out.println("Rijec: "+vektor2.get(i).getRijec()+". Frekvencija: "+vektor2.get(i).getFrekvencija());
		}
		System.out.println("\n");
	}
	
}
