package hr.java.vjezbe.entitet;

public class Predmet {

	private String sifra;
	private String naziv;
	private Integer brojEctsBodova;
	private Profesor nositelj;
	private Student student[];
	private Integer pomBrojStudenta;
	
	
	public Predmet(String sifra, String naziv, Integer brojEctsBodova, Profesor nositelj,Integer pomBrojStudenta) {
		this.sifra = sifra;
		this.naziv = naziv;
		this.brojEctsBodova = brojEctsBodova;
		this.nositelj = nositelj;
		this.pomBrojStudenta=pomBrojStudenta;

	}
	public String getSifra() {
		return sifra;
	}


	public void setSifra(String sifra) {
		this.sifra = sifra;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public Integer getBrojEctsBodova() {
		return brojEctsBodova;
	}


	public void setBrojEctsBodova(Integer brojEctsBodova) {
		this.brojEctsBodova = brojEctsBodova;
	}


	public Profesor getNositelj() {
		return nositelj;
	}


	public void setNositelj(Profesor nositelj) {
		this.nositelj = nositelj;
	}


	public Student[] getStudenti() {
		return student;
	}


	public void setStudenti(Student[] studenti) {
		this.student = studenti;
	}
	public Student[] getStudent() {
		return student;
	}
	public void setStudent(Student[] student) {
		this.student = student;
	}
	public Integer getPomBrojStudenta() {
		return pomBrojStudenta;
	}
	public void setPomBrojStudenta(Integer pomBrojStudenta) {
		this.pomBrojStudenta = pomBrojStudenta;
	}
	
	
	
	
}
