public class consoleProgress {
    public void loginAndOut(String status) throws InterruptedException{
        
    
            
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("\t\n"+consoleColors.RED_BACKGROUND_BRIGHT+consoleColors.BLACK_BOLD_BRIGHT+status+consoleColors.RESET);
            Thread.sleep(1000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.print(consoleColors.YELLOW_BOLD_BRIGHT+"Redirecting");
            repeatCharacters.slowChars("...."+consoleColors.RESET,300);
            Thread.sleep(1000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
    }
    
        public void progress(String name) throws InterruptedException {
            char[] animationChars = new char[]{'|', '/', '-', '\\'};
    
            for (int i = 0; i <= 100; i++) {
                System.out.print(consoleColors.CYAN_BOLD_BRIGHT+name+": " + i + "% " + animationChars[i % 4] + "\r"+consoleColors.RESET);
    
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("\t\n"+consoleColors.RED_BACKGROUND_BRIGHT+consoleColors.BLACK_BOLD_BRIGHT+name+": Done"+consoleColors.RESET);
            Thread.sleep(1000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.print(consoleColors.YELLOW_BOLD_BRIGHT+"Redirecting");
            repeatCharacters.slowChars("...."+consoleColors.RESET,300);
            Thread.sleep(1000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
    
    }

    public void order() throws InterruptedException{
        char[] animationChars = new char[]{'|', '/', '-', '\\'};
    
            for (int i = 0; i <= 100; i++) {
                System.out.print(consoleColors.CYAN_BOLD_BRIGHT+"Processing Order"+": " + i + "% " + animationChars[i % 4] + "\r"+consoleColors.RESET);
    
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("\t\n"+consoleColors.YELLOW_BOLD_BRIGHT+"Confirming Order"+consoleColors.RESET);
            Thread.sleep(1000);
            System.out.print("\033[H\033[2J");
            System.out.println("\t\n"+consoleColors.YELLOW_BOLD_BRIGHT+"Thankyou for ordering from Meal Dash\nHave a Nice Day"+consoleColors.RESET);
            Thread.sleep(1000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.print(consoleColors.YELLOW_BOLD_BRIGHT+"Redirecting");
            repeatCharacters.slowChars("...."+consoleColors.RESET,300);
            Thread.sleep(1000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
    }
}
