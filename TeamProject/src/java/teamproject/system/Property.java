package teamproject.system;

import java.time.LocalDate;

    /**
     * For ease of use, avoid spelling mistakes when setting/getting properties.
     * Can assign a default value using the constructor.
     * Override getDefaultValue in special cases. 
     */
    public enum Property
    {
        DummyAdminUserName("admin"),
        DummyAdminPassword("password"),
        
        DatabaseUrl("localhost"),
        DatabasePort("3306"),
        DatabaseUser("admin"),
        DatabasePassword("password"),
        DatabaseName("schedulerdatabase"),
        MySQLPath("C:/xampp/mysql/bin"),
        MySQLFileName("mysql.exe"),
        MySQLDumpFileName("mysqldump.exe"),
        BackUpPath("C:/Users/drgex_000/Desktop/test"),
        BackUpName("TimeTable_Backup"),
        
        BlockedEmailServices,
        AllowedEmailServices,

        //not used
        //MinimumManualDaysInactive("100"),
        //AutoRemoveAfterDaysInactive("365"),
        SystemStartDate(LocalDate.now().toString())
        {
            private String toHTMlinput()
            {
                return "";
            }
        };
        

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
    }
