package teamproject.system;

import java.time.LocalDate;

 //for ease of use, avoid spelling misstakes when setting/getting properties.
    public enum Property
    {
        DatabaseUrl("localhost"),
        DatabaseUser,
        DatabasePassword,

        BlockedEmailServices,
        AllowedEmailServices,


        
        BackUpName("TimeTable_Backup"),
        NumberOfBackUp("3"),
        NextBackUpNumber("1"),
        BackUpEvery("D1"),
        BackUpUrl("/backup"),
        
        MinimumManualDaysInactive("45"),
        AutoRemoveAfterDaysInactive("60"),
        SystemStartDate
        {
            @Override
            public String getDefaultValue() { return  LocalDate.now().toString(); }
        };

        private final String defaultValue;
        
        private Property(String defaultValue) { this.defaultValue = defaultValue; }
        private Property() { this.defaultValue = ""; }  
        
        public String getDefaultValue() { return defaultValue; }
    }
