package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {

	public FakultetRacunarstva(Predmet[] predmeti, Profesor[] profesori, Student[] studenti,
			Ispit[] ispiti) {
		super(predmeti, profesori, studenti, ispiti);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Student odrediStudentaZaRektorovuNagradu() {
		Integer maxProsjek = 0;
		Integer Zbroj=0;
		Integer best=0;
		int godina1;
		int godina2;
		for (int i = 0 ;i<getStudenti().length;i++) {
			for (int j = 0 ;j<getStudenti().length;j++) {
				if (getIspiti()[i].getStudent().equals(getStudenti()[j])) {
					Zbroj=Zbroj + getIspiti()[i].getOcjena();
				}
			}
			if (maxProsjek < Zbroj ) {
				maxProsjek = Zbroj;
				best = i;
			}else if (maxProsjek == Zbroj) {
				godina1 = getStudenti()[best].getDatumRodjenja().getYear();
				godina2= getStudenti()[i].getDatumRodjenja().getYear();
				if (godina1 > godina2) {
					
				}else if(godina1 < godina2) {
					best = i;
				}
			}
			Zbroj = 0;
			
		}
		Student najbolji = new Student (getStudenti()[best].getIme(),getStudenti()[best].getPrezime(),getStudenti()[best].getJmbg(),getStudenti()[best].getDatumRodjenja());

		return najbolji;
	}
	
	
	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {
		Integer max = 0;
		Integer petice = 0;
		Integer best = 0;
		
		for(int i=0;i<getStudenti().length;i++) {
			for (int j=0;j<getStudenti().length;j++) {
				if(getStudenti()[i].equals(getIspiti()[j].getStudent()) && getIspiti()[j].getOcjena() == 5) {
					petice++;
				}if( petice > max) {
					best = i;
					max=petice;
					petice =0;
				}else {
					petice =0;
				}
			}
		}
		Student najbolji = new Student (getStudenti()[best].getIme(),getStudenti()[best].getPrezime(),getStudenti()[best].getJmbg(),getStudenti()[best].getDatumRodjenja());
		
		return najbolji;
	}
	
	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispit, Integer ocjenaDiplomskog,Integer ocjenaObraneDiploskog) {
		BigDecimal prosjecnaOcjenaStudenta =  odrediProsjekOcjenaNaIspitima(getIspiti());
     	BigDecimal konacna = new BigDecimal(3).multiply(prosjecnaOcjenaStudenta).add(new BigDecimal(ocjenaDiplomskog+ocjenaObraneDiploskog)).divide(new BigDecimal(5));
		return konacna;
	}
	

}
