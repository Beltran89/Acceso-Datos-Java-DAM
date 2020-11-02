package enums;

public enum TamanioCafe {
	PEQUE�O(5),
	MEDIANO(10),
	GRANDE(20);
	
	private int capacidad;
	
	TamanioCafe(int capacidad){
		this.capacidad = capacidad;
	}
	
	public int getCapacidad(){
		return this.capacidad;
	}
}
