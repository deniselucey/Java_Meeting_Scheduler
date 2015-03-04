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
        
        BlockedEmailServices,
        AllowedEmailServices,

        BackUpName("TimeTable_Backup"),
        //NumberOfBackUp("3"),
        //NextBackUpNumber("1"),
        //BackUpEvery("D1"),
        BackUpUrl("/backup"),
        
        MinimumManualDaysInactive("100"),
        AutoRemoveAfterDaysInactive("365"),
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
            String html = "<div><label>";
            html += this.name();
            html += "</label>";
            html += "<input type=\"text\" name=\"" + this.name() + "\" value=\"\"/><br>";
            html += "</div>";
            return html;
        }
        
        public static String toHTMLForm()
        {
            String html = "<form>";
            
            for(Property p : Property.values())
            {
                p.toHTMlinput();
            }
            html += "</form>";
            return html;
        }
    }
