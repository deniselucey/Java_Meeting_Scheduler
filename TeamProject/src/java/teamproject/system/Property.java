package teamproject.system;

import java.time.LocalDate;
import java.util.Arrays;

    /**
     * For ease of use, avoid spelling mistakes when setting/getting properties.
     * Can assign a default value using the constructor.
     * Override getDefaultValue in special cases. 
     */
    public enum Property
    {        
        DatabaseUrl("localhost"),
        DatabasePort("3306"),
        DatabaseUser("admin"),
        DatabasePassword("password"),
        DatabaseName("schedulerdatabase"),//Do Not Change 
        MySQLPath("C:/xampp/mysql/bin"),
        MySQLFileName("mysql.exe"),
        MySQLDumpFileName("mysqldump.exe"),
        BackUpPath("./backup"),
        BackUpName("TimeTable_Backup"),
        
        BlockedEmailServices,
        AllowedEmailServices;
        
//        SystemStartDate(LocalDate.now().toString())
//        {
//            private String toHTMlinput()
//            {
//                return "";
//            }
//        };
        

        private final String defaultValue;
        
        private Property(String defaultValue) { this.defaultValue = defaultValue; }
        private Property() { this.defaultValue = ""; }  
        
        public String getDefaultValue() { return defaultValue; }
        
        private String toHTMlinput()
        {
            String html = "<div><label class='noFloat'>";
            html += this.name();
            html += "</label>";
            html += "<input id=\""+this.name()+"\"type=\"text\" name=\"" + this.name() + "\" value=\"\"/>";
            html += "<input type=\"submit\" value=\"Update\"  onclick=\"save('" +this.name() + "')\" />";
            
//            html += "<input type=\"submit\" value=\"Update\" onclick=\"save(\""+this.name()+"\")\"/>";
                
            html += "</div>";
            return html;
        }
        
        public static String toHTMLForm()
        {
            String html ="";// = "<div>";
            
            for(Property p : Property.values())
            {  
               html += "<div>";
               html += p.toHTMlinput();
               html += "</div>";
            }
            
            return html;
        }
        /**
         * Return Property who's name matches the value
         * @param name
         * @return  Property who's name matches the value.
         */
        public static Property getByName(String name)
        {
            return Arrays.stream(Property.values()).filter(p -> p.name().equals(name)).findFirst().get();
        }
    }
