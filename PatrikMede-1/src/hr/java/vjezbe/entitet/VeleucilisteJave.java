package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska{

	public VeleucilisteJave(Predmet[] predmeti, Profesor[] profesori, Student[] studenti,
			Ispit[] ispiti) {
		super(predmeti, profesori, studenti, ispiti);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispit, Integer ocjenaPismenogZavrsnog,Integer ocjenaObraneZavrsnog) {
		
		BigDecimal prosjecnaOcjenaStudenta =  odrediProsjekOcjenaNaIspitima(getIspiti());
		
     	BigDecimal konacna = new BigDecimal(2).multiply(prosjecnaOcjenaStudenta).add(new BigDecimal(ocjenaPismenogZavrsnog+ocjenaObraneZavrsnog)).divide(new BigDecimal(4));
     			
		return konacna;	
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
					
				}
			}
		}
		Student najbolji = new Student (getStudenti()[best].getIme(),getStudenti()[best].getPrezime(),getStudenti()[best].getJmbg(),getStudenti()[best].getDatumRodjenja());
		
		return najbolji;
	}
	


}
