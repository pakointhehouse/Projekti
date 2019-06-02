package hr.java.vjezbe.glavna;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.entitet.FakultetRacunarstva;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.ObrazovnaUstanova;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.entitet.VeleucilisteJave;

public class Glavna {

	public static final Integer brojProfesora=2;
	public static final Integer brojPredmeta=2;
	public static final Integer brojStudenata=2;
	public static final Integer brojIspita=2;
	public static final Integer brojObrazovnihUstanova=2;
	
	public static Profesor[] prof= new Profesor[brojProfesora];
	public static Predmet[] pred= new Predmet[brojPredmeta];
	public static Student[] stud = new Student[brojStudenata];
	public static Ispit[] ispit = new Ispit[brojIspita];
	public static ObrazovnaUstanova obrazovna;
	private static final Logger Logger = LoggerFactory.getLogger(Glavna.class);
	
	public static void main(String[] args) {
	         
		 
		 Scanner unos = new Scanner(System.in);
		 
		for (int z = 0;z<brojObrazovnihUstanova ;z++) {
			 for (int i=0;i<brojProfesora;i++) {
				 System.out.println("Unesite "+(i+1)+". profesora");
				 prof[i]=unesiProfesora(unos);
			 }
			
			 for (int i=0;i<brojPredmeta;i++) {
				 System.out.println("Unesite "+(i+1)+". predmet");
				 pred[i]=unesiPredmet(unos);
			 }
			 
			 for (int i=0;i<brojStudenata;i++) {
				 System.out.println("Unesite "+(i+1)+". studenta");
				 stud[i]=unesiStudenta(unos);
			 }
			 
			 for (int i=0;i<brojIspita;i++) {
				 System.out.println("Unesite "+(i+1)+". ispit");
				 ispit[i]=unesiIspit(unos);
			 }
			 
			 izvrstanStudent();
			 odabirUstanove(z,unos);
			 
			}
		
			
		    
		}

	
	 
	private static Profesor unesiProfesora(Scanner unos){
		System.out.println("Unesite šifru profesora:");
		String pomSifra = unos.nextLine();
		System.out.println("Unesite ime profesora:");
		String pomIme = unos.nextLine();
		System.out.println("Unesite prezime profesora:");
		String pomPrezime = unos.nextLine();
		System.out.println("Unesite titulu profesora:");
		String pomTitula = unos.nextLine();
		return new Profesor(pomSifra,pomIme,pomPrezime,pomTitula);
	}
	 
	private static Predmet unesiPredmet(Scanner unos) {
		boolean pogodio=false;
		Integer pomEcts = null;
		Integer pomOdabir = 0;
		Integer pomBrojStudenata =null;
		System.out.println("Unesite sifru predmeta:");
		String pomSifra = unos.nextLine();
		System.out.println("Unesite naziv predmeta:");
		String pomNaziv = unos.nextLine();
		do {
			try {
				System.out.println("Unesite broj ECTS bodova za predmet '"+pomNaziv+"':");
				pomEcts = unos.nextInt();
				Logger.info("Unesen je broj "+pomEcts);
				pogodio=true;
			}catch(InputMismatchException input){
				System.out.println("Krivi Unos");
				Logger.info("ECTS bodovi su krivo uneseni");
				unos.nextLine();
			}
		}while(pogodio==false);
		pogodio = false;
		unos.nextLine();
		do {
			try {
				System.out.println("Odaberite profesora");
				for (int i = 0 ; i<brojProfesora;i++) {
					System.out.println((i+1)+prof[i].getIme()+" "+prof[i].getPrezime());
				}
				pomOdabir = unos.nextInt();
			}catch(InputMismatchException input) {
				System.out.println("Krivi Unos");
				Logger.info("Odaberite ispravnog profesora");
				unos.nextLine();
			}
		}while(pomOdabir<1 || pomOdabir>2);
		
		
		pogodio = false;
		
		do {
			try {
				System.out.println("Unesite broj studenata za predmet "+ pomNaziv);
				pomBrojStudenata = unos.nextInt();
				pogodio = true;
			}catch(InputMismatchException input){
				System.out.println("Krivi Unos");
				Logger.info("Broj studenata je krivo unesen");
				pogodio= false;
				unos.nextLine();
			}
		}while(pogodio==false);
		
		unos.nextLine();
		return new Predmet(pomSifra,pomNaziv,pomEcts,prof[pomOdabir-1],pomBrojStudenata);
	}
	
	private static Student unesiStudenta(Scanner unos) {
		boolean pogodio = false;
		LocalDate dateTime=null;
		System.out.println("Unesite ime studenta");
		String pomIme = unos.nextLine();
		System.out.println("Unesite prezime studenta");
		String pomPrezime = unos.nextLine();
		do {
			try {
				System.out.println("Unesite datum rodenja studenta"+pomIme+" "+pomPrezime+" u formatu (dd.MM.yyyy.):");
				String pomDatum = unos.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				dateTime = LocalDate.parse(pomDatum, formatter);
				pogodio = true;
			}catch(DateTimeParseException e) {
				System.out.println("Krivi Unos");
				Logger.info("Neispravno unesen datum rodenja");
				unos.nextLine();
			}
		
		}while(pogodio == false);
		System.out.println("Unesite jmbag studenta "+pomIme+" "+pomPrezime+":");
		String pomJmbag = unos.nextLine();
		return new Student(pomIme,pomPrezime,pomJmbag,dateTime);
	}
	
	private static Ispit unesiIspit(Scanner unos) {
		Integer pomOdabirPred=0;
		Integer pomOcjena=0;
		Integer pomOdabirStud=0;
		LocalDateTime dateTime = null;
		boolean pogodio =false;
		
		do {
			try {
		System.out.println("Odaberite predmet");
		for (int i = 0;i<brojPredmeta;i++) {
			System.out.println((i+1)+"."+pred[i].getNaziv());
		}
		pomOdabirPred = unos.nextInt();
			}catch(InputMismatchException input) {
				System.out.println("Krivi Unos");
				Logger.info("Predmet je krivo unesen");
				unos.nextLine();
			}
		}while(pomOdabirPred<1 || pomOdabirPred>2);
		unos.nextLine();
		do {
			try {
		
				System.out.println("Odaberite studenta");
				for (int i = 0;i<brojStudenata;i++) {
					System.out.println((i+1)+"."+stud[i].getIme()+" "+stud[i].getPrezime());
				}
				pomOdabirStud = unos.nextInt();
			}catch(InputMismatchException input) {
				System.out.println("Krivi Unos");
				Logger.info("Student je krivo unesen");
				unos.nextLine();
				}
			}while(pomOdabirStud<1 || pomOdabirStud>2);
		unos.nextLine();
		do {
			try {
		
				System.out.println("Unesite ocjenu na ispitu");
				pomOcjena = unos.nextInt();
				unos.nextLine();
				pogodio = true;
			}catch(InputMismatchException input) {
				System.out.println("Krivi Unos");
				Logger.info("Ocjena krivo unesena");
				unos.nextLine();
			}	
		}while(pogodio==false);
		pogodio = false;
		
		do {
			try {
				System.out.println("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyy.THH:mm):");
				String pomDatum = unos.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
				dateTime = LocalDateTime.parse(pomDatum, formatter);
				pogodio = true;
			}catch(DateTimeParseException e) {
				System.out.println("Krivi Unos");
				Logger.info("Datum krivo unesen");
				}
			}while(pogodio==false);
		return new Ispit(pred[pomOdabirPred-1],stud[pomOdabirStud-1],pomOcjena,dateTime);
		
	}
	private static void odabirUstanove(int z,Scanner unos) {
		boolean provjeri = false;
		Integer pomOdabir = 0;
		do {
			try {
				System.out.println("Odaberite obrazovnu ustanovu za navedene podatke koju želite unijeti");
				pomOdabir = unos.nextInt();
				provjeri = true;
			}catch(InputMismatchException e) {
				System.out.println("Krivi Unos");
				Logger.info("Obrazovna ustanova krivo unesena");
				unos.nextLine();
				}
		}while(provjeri==false);
		
		
		if(pomOdabir == 1) {
			unosVeleuciliste(unos,z);
		}else {
			unosRacunarstvo(unos,z);
		}
		unos.nextLine();
	}
	
	private static FakultetRacunarstva unosRacunarstvo(Scanner unos,Integer j) {
		boolean provjeri = false;
		Integer ocjenaZavrsnog=0;
		Integer ocjenaObrane=0;
		FakultetRacunarstva racunarstvo = new FakultetRacunarstva(pred,prof,stud,ispit);
		System.out.print("Unesite naziv obrazovne ustanove: ");
		String nazivUstanove = unos.nextLine();
		racunarstvo.setNaziv(nazivUstanove);
		
		do {
			try {
				System.out.printf("Unesite ocjenu završnog rada za studenta: %s %s  ",ispit[j].getStudent().getIme(),ispit[j].getStudent().getPrezime());
				ocjenaZavrsnog = unos.nextInt();
				provjeri = true;
			}catch(InputMismatchException e) {
				System.out.println("Krivi Unos");
				Logger.info("Krivo unesena ocjena");
				unos.nextLine();
				}
			}while(provjeri==false);
		
		unos.nextLine();
		provjeri=false;
		
		do {
			try {
		
				System.out.printf("Unesite ocjenu obrane završnog rada: %s %s  ",ispit[j].getStudent().getIme(),ispit[j].getStudent().getPrezime());
				ocjenaObrane = unos.nextInt();
				provjeri=true;
				}catch(InputMismatchException e) {
					System.out.println("Krivi Unos");
					Logger.info("Krivo unesena ocjena");
					unos.nextLine();
					}
				}while(provjeri==false);
		
		
		BigDecimal konacnaOcjena=racunarstvo.izracunajKonacnuOcjenuStudijaZaStudenta(ispit, ocjenaZavrsnog, ocjenaObrane);
		konacnaOcjena = konacnaOcjena.setScale(0, RoundingMode.HALF_UP);
		System.out.printf("Konačna ocjena studija studenta %s %s je : ",ispit[j].getStudent().getIme(),ispit[j].getStudent().getPrezime());
		System.out.print(konacnaOcjena);
		System.out.printf("\n");
		return racunarstvo;
	}
	
	private static VeleucilisteJave unosVeleuciliste(Scanner unos,Integer j) {
		boolean provjeri = false;
		Integer ocjenaZavrsnog=0;
		Integer ocjenaObrane=0;
		VeleucilisteJave veleuciliste = new VeleucilisteJave(pred,prof,stud,ispit);	
		System.out.print("Unesite naziv obrazovne ustanove: ");
		String nazivUstanove = unos.nextLine();
		veleuciliste.setNaziv(nazivUstanove);
		
		do {
			try {
				System.out.printf("Unesite ocjenu završnog rada za studenta: %s %s  ",ispit[j].getStudent().getIme(),ispit[j].getStudent().getPrezime());
				ocjenaZavrsnog = unos.nextInt();
				provjeri=true;
			}catch(InputMismatchException e) {
				System.out.println("Krivi Unos");
				Logger.info("Krivo unesena ocjena");
				unos.nextLine();
				}
			}while(provjeri==false);
		
		unos.nextLine();
		provjeri=false;
		
		do {
			try {
				System.out.printf("Unesite ocjenu obrane završnog rada: %s %s  ",ispit[j].getStudent().getIme(),ispit[j].getStudent().getPrezime());
				ocjenaObrane = unos.nextInt();
				provjeri=true;
			}catch(InputMismatchException e) {
				System.out.println("Krivi Unos");
				Logger.info("Krivo unesena ocjena");
				unos.nextLine();
				}
			}while(provjeri==false);
		
		BigDecimal konacnaOcjena=veleuciliste.izracunajKonacnuOcjenuStudijaZaStudenta(ispit, ocjenaZavrsnog, ocjenaObrane);
		konacnaOcjena = konacnaOcjena.setScale(0, RoundingMode.HALF_UP);
		System.out.printf("Konačna ocjena studija studenta %s %s je : ",ispit[j].getStudent().getIme(),ispit[j].getStudent().getPrezime());
		System.out.print(konacnaOcjena);
		System.out.printf("\n");
		return veleuciliste;
	}
	
	private static void izvrstanStudent() {
		for(int i=0;i<brojIspita;i++) {
			for (int j =0 ;j<brojStudenata;j++) {
					if(ispit[i].getStudent().equals(stud[j]) && ispit[i].getOcjena()==5) {
				System.out.println(ispit[i].getStudent().getIme()+" "
			+ispit[i].getStudent().getPrezime()+
		"Je ostavirio ocjenu izvrstan na predmetu"+ispit[i].getPredmet().getNaziv()+".");
					}
				}
			}
		}
	
	
}
