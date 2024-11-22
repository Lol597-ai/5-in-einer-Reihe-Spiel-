public class SpielDaten {
    
	private char[][] dieDaten; // Array zum Speichern des Spielzustands
	SpielOberflaeche dieSpielOberflaeche;

	public SpielDaten() {
		
		dieDaten = new char[8][10];
		loescheDaten();
		
	}
	public void schreibeZeichen(int x, int y, char z) {
		
		dieDaten[x][y] = z;
		
    }
	
	private int gibLinienLaengeR1(int x, int y) {
		
		//Rückgabe Counter
		int MaxCount = 0;
		int count    = 0;

		for(int i = 0; i <= 7;i++) {
			if(dieDaten[i][y] == dieDaten[x][y]) {
				count++;
				if(count > MaxCount){
					MaxCount = count;
				}	
			}else {
				count = 0;
			}
					
		}  
		return MaxCount;
	}
	
	private int gibLinienLaengeR2(int x, int y) {
		
		//Rückgabe Counter
		int MaxCount = 0;
		int count    = 0;

		for(int i = 0; i <= 9;i++) {
			if(dieDaten[x][i] == dieDaten[x][y]) {
				count++;
				if(count > MaxCount){
					MaxCount = count;
				}	
			}else {
				count = 0;
			}
					
		}  
		return MaxCount;

	}
	
	private int gibLinienLaengeR3(int x, int y) {
		
		//Rückgabe Counter
		int MaxCount = 0;
		int count    = 0;

		int startX = x;
		int startY = y;

		while(startX > 0 && startY > 0 ){
			startX--;
			startY--;
		}
		while(startX <= 7 && startY <= 9) {
			if(dieDaten[startX][startY] == dieDaten[x][y]) {
				count++;
				if(count > MaxCount) {
					MaxCount = count;
				}
			} else {
				count = 0;
		    	}
				
				startX++;
				startY++;
		}
		return MaxCount;
	}
	
	private int gibLinienLaengeR4(int x, int y) {
		
		//Rückgabe Counter
		int MaxCount = 0;
		int count    = 0;

		int startX = x;
		int startY = y;

		while(startX < 7 && startY > 0 ){
			
			startX++;
			startY--;

		}
		while(startX >= 0 && startY <= 9) {
			if(dieDaten[startX][startY] == dieDaten[x][y]) {
				count++;
				if(count > MaxCount) {
					MaxCount = count;
				}
			} else {
				count = 0;
		    	}
				
				startX--;
				startY++;
		}
		return MaxCount;

	}
	
	public void loescheDaten() {
		for (int i = 0; i < dieDaten.length; i++) {
            for (int j = 0; j < dieDaten[i].length; j++) {
                dieDaten[i][j] = 'F';
            }
        }
    }
	public char leseZeichen(int x, int y) {
		
			return dieDaten[x][y];		
	}
	
	 int gibMaxLinienLaenge(int x, int y){

			int maxLaenge = Math.max(gibLinienLaengeR1(x, y), Math.max(gibLinienLaengeR2(x, y),Math.max(gibLinienLaengeR3(x, y), gibLinienLaengeR4(x, y)))
		);
		return maxLaenge;
    }

	 }
	 
	
	
	
	

