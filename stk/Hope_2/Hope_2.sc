stk.v.11.0
WrittenBy    STK_v11.2.0
BEGIN Scenario
    Name            Hope_2

BEGIN Epoch

    Epoch        10 Jul 2026 06:30:00.000000000
    SmartEpoch
	BEGIN	EVENT
			Epoch	10 Jul 2026 06:30:00.000000000
			EventEpoch
				BEGIN	EVENT
					Type	EVENT_LINKTO
					Name	AnalysisStartTime
				END	EVENT
			EpochState	Implicit
	END	EVENT


END Epoch

BEGIN Interval

Start                   10 Jul 2026 06:30:00.000000000
Stop                    11 Jul 2026 06:30:00.000000000
    SmartInterval
	BEGIN	EVENTINTERVAL
			BEGIN Interval
				Start	10 Jul 2026 06:30:00.000000000
				Stop	11 Jul 2026 06:30:00.000000000
			END Interval
			IntervalState	Explicit
	END	EVENTINTERVAL

EpochUsesAnalStart      No
AnimStartUsesAnalStart  Yes
AnimStopUsesAnalStop    Yes

END Interval

BEGIN EOPFile

    EOPFilename     EOP-v1.1.txt

END EOPFile

BEGIN GlobalPrefs

    SatelliteNoOrbWarning    No
    MissilePerigeeWarning    No
    MissileStopTimeWarning   No
    AircraftWGS84Warning     Always
END GlobalPrefs

BEGIN CentralBody

    PrimaryBody     Earth

END CentralBody

BEGIN CentralBodyTerrain

    BEGIN CentralBody
        Name            Earth
        UseTerrainCache Yes
        TotalCacheSize  402653184

        BEGIN StreamingTerrain
            UseCurrentStreamingTerrainServer     No
            StreamingTerrainServerName           assets.agi.com/stk-terrain/
            StreamingTerrainAzimuthElevationMaskEnabled       No
            StreamingTerrainObscurationEnabled       No
            StreamingTerrainCoverageGridObscurationEnabled       No
        END StreamingTerrain
    END CentralBody

END CentralBodyTerrain

BEGIN StarCollection

    Name     Hipparcos 2 Mag 6

END StarCollection

BEGIN ScenarioLicenses
    Module    AMMv11.2
    Module    ASTGv11.2
    Module    CATv11.2
    Module    CHAINSv11.2
    Module    CONv11.2
    Module    COVv11.2
    Module    CRMv11.2
    Module    Commv11.2
    Module    DISv11.2
    Module    EOIRv11.2
    Module    HRMv11.2
    Module    MexServv11.2
    Module    RT3Clientv11.2
    Module    RdrAdvEnv11.2
    Module    SEETv11.2
    Module    SOLISv11.2
    Module    STKCAP
    Module    STKExpertv11.2
    Module    STKIntegrationv11.2
    Module    STKParallelComputingv11.2
    Module    STKProfessionalv11.2
    Module    STKv11.2
    Module    TERNv11.2
    Module    TIREMv11.2
    Module    UPropv11.2
    Module    Underseav11.2
END ScenarioLicenses

BEGIN QuickReports

    BEGIN Report
        Name    Access
        Type    Report
        BaseDir Install
        Style    Access
        AGIViewer    Yes
        Instance    Satellite/ADEOS-II_27597
        BEGIN InstanceList
            Instance    Satellite/ADEOS_24277
            Instance    Satellite/ADEOS-II_27597
            Instance    Satellite/CARTOSAT-1_28649
            Instance    Satellite/CARTOSAT-2_29710
            Instance    Satellite/CARTOSAT-2A_32783
            Instance    Satellite/CARTOSAT-2B_36795
            Instance    Satellite/CARTOSAT-2C_41599
            Instance    Satellite/CARTOSAT-2D_41948
            Instance    Satellite/DUBAISAT-1_35682
            Instance    Satellite/DUBAISAT-2_39419
            Instance    Satellite/DZZ-HR_39731
            Instance    Satellite/FAISAT_23465
            Instance    Satellite/FAISAT_2V_24954
            Instance    Satellite/GEODETIC_EARTH_07734
            Instance    Satellite/GEOS_2_03093
            Instance    Satellite/GEOSAT_15595
            Instance    Satellite/JUGNU_37839
            Instance    Satellite/KAZEOSAT_2_40010
            Instance    Satellite/NEOSSAT_39089
            Instance    Satellite/QSAT-EOS_40301
            Instance    Satellite/RISAT-1_38248
            Instance    Satellite/RISAT-2_34807
            Instance    Satellite/SARAL_39086
            Instance    Satellite/SCATSAT_1_41790
            Instance    Satellite/SRMSAT_37841
            Instance    Satellite/SWAYAM_41607
            Instance    Satellite/TELEOS_1_41169
            Instance    Satellite/THEOS_33396
            Instance    Satellite/TIGRISAT_40043
            Instance    Satellite/WEOS_27599
            Instance    Satellite/YOUTHSAT_37388
        END InstanceList
        BEGIN TimeData
            BEGIN Section
                SectionNumber    1
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    2
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    3
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    4
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    5
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    6
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    7
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    8
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    9
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    10
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    11
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    12
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    13
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    14
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    15
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    16
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    17
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    18
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    19
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    20
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    21
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    22
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    23
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    24
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    25
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    26
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    27
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    28
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    29
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    30
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    31
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    32
                SectionType      4
                ShowIntervals    No
                TimeType    Availability
                SamplingType    Default
                TimeBound    0
            END Section
        END TimeData
        DisplayOnLoad     Yes
        FrameType         0
        DockCircleID      0
        DockID            0
        WindowRectLeft    510
        WindowRectTop     281
        WindowRectRight   1851
        WindowRectBottom  782
    END Report

    BEGIN Report
        Name    Close Approach By Min Sep
        Type    Report
        BaseDir Install
        Style    Close Approach By Min Sep
        AGIViewer    Yes
        Instance    AdvCAT/AdvCAT1
        BEGIN TimeData
            BEGIN Section
                SectionNumber    1
                SectionType      1
                ShowIntervals    No
BEGIN IntervalList

DateUnitAbrv UTCG

BEGIN Intervals

    "10 Jul 2026 06:30:00.000000000" "11 Jul 2026 06:30:00.000000000"
END Intervals

END IntervalList

                TimeType    Interval
                TimeInterval 	IntervalTimePeriod
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    2
                SectionType      1
                ShowIntervals    No
BEGIN IntervalList

DateUnitAbrv UTCG

BEGIN Intervals

    "10 Jul 2026 06:30:00.000000000" "11 Jul 2026 06:30:00.000000000"
END Intervals

END IntervalList

                TimeType    Interval
                TimeInterval 	IntervalTimePeriod
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    3
                SectionType      1
                ShowIntervals    No
BEGIN IntervalList

DateUnitAbrv UTCG

BEGIN Intervals

    "10 Jul 2026 06:30:00.000000000" "11 Jul 2026 06:30:00.000000000"
END Intervals

END IntervalList

                TimeType    Interval
                TimeInterval 	IntervalTimePeriod
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    4
                SectionType      1
                ShowIntervals    No
BEGIN IntervalList

DateUnitAbrv UTCG

BEGIN Intervals

    "10 Jul 2026 06:30:00.000000000" "11 Jul 2026 06:30:00.000000000"
END Intervals

END IntervalList

                TimeType    Interval
                TimeInterval 	IntervalTimePeriod
                SamplingType    Default
                TimeBound    0
            END Section
            BEGIN Section
                SectionNumber    5
                SectionType      4
                ShowIntervals    No
BEGIN IntervalList

DateUnitAbrv UTCG

BEGIN Intervals

    "10 Jul 2026 06:30:00.000000000" "11 Jul 2026 06:30:00.000000000"
END Intervals

END IntervalList

                TimeType    Interval
                TimeInterval 	IntervalTimePeriod
                SamplingType    Default
                TimeBound    0
            END Section
        END TimeData
        DisplayOnLoad     Yes
        FrameType         0
        DockCircleID      0
        DockID            0
        WindowRectLeft    341
        WindowRectTop     83
        WindowRectRight   1924
        WindowRectBottom  855
    END Report
END QuickReports

BEGIN WebData
        EnableWebTerrainData    No
        SaveWebTerrainDataPasswords    No
        BEGIN ConfigServerDataList
            BEGIN ConfigServerData
                Name "globeserver.agi.com"
                Port 80
                DataURL "bin/getGlobeSvrConfig.pl"
            END ConfigServerData
        END ConfigServerDataList
END WebData

BEGIN Extensions
    
    BEGIN ClsApp
		RangeConstraint         5000.000
		ApoPeriPad              30000.000
		OrbitPathPad            100000.000
		TimeDistPad             30000.000
		OutOfDate               2592000.000
		MaxApoPeriStep          900.000
		ApoPeriAngle            0.785
		UseApogeePerigeeFilter  Yes
		UsePathFilter           No
		UseTimeFilter           No
		UseOutOfDate            Yes
		CreateSats              No
		MaxSatsToCreate         500
		UseModelScale           No
		ModelScale              0.000
		UseCrossRefDb           Yes
		CollisionDB                     stkAllTLE.tce
		CollisionCrossRefDB             stkAllTLE.sd
		ShowLine                Yes
		AnimHighlight           Yes
		StaticHighlight         Yes
		UseLaunchWindow                         No
		LaunchWindowUseEntireTraj               Yes
		LaunchWindowTrajMETStart                0.000
		LaunchWindowTrajMETStop                 900.000
		LaunchWindowStart                       0.000
		LaunchWindowStop                        0.000
		LaunchMETOffset                         0.000
		LaunchWindowUseSecEphem                 No 
		LaunchWindowUseScenFolderForSecEphem    Yes
		LaunchWindowUsePrimEphem                No 
		LaunchWindowUseScenFolderForPrimEphem   Yes
    LaunchWindowIntervalPtr
	BEGIN	EVENTINTERVAL
			BEGIN Interval
				Start	10 Jul 2026 06:30:00.000000000
				Stop	11 Jul 2026 06:30:00.000000000
			END Interval
			IntervalState	Explicit
	END	EVENTINTERVAL

		LaunchWindowUsePrimMTO                  No 
		GroupLaunches                           No 
		LWTimeConvergence                       1.000e-03
		LWRelValueConvergence                   1.000e-08
		LWTSRTimeConvergence                    1.000e-04
		LWTSRRelValueConvergence                1.000e-10
		LaunchWindowStep                        300.000
		MaxTSRStep                              180.000
		MaxTSRRelMotion                         20.000
		UseLaunchArea                           No 
		LaunchAreaOrientation                   North
		LaunchAreaAzimuth                       0.000
		LaunchAreaXLimits                       -10000.000   10000.000
		LaunchAreaYLimits                       -10000.000   10000.000
		LaunchAreaNumXIntrPnts                  1
		LaunchAreaNumYIntrPnts                  1
		LaunchAreaAltReference                  Ellipsoid
		TargetSameStop                          No 
		SkipSurfaceMetric                       No 
		LWAreaTSRRelValueConvergence            1.000e-10
		AreaLaunchWindowStep                    300.000
		AreaMaxTSRStep                          30.000
		AreaMaxTSRRelMotion                     1.000
		ShowLaunchArea                          No 
		ShowBlackoutTracks                      No 
		ShowClearedTracks                       No 
		UseObjectForClearedColor                No 
		BlackoutColor                           #ff0000
		ClearedColor                             #ffffff
		ShowTracksSegments                      Yes
		ShowMinRangeTracks                      Yes
		MinRangeTrackTimeStep                   0.500000
		UsePrimStepForTracks                    Yes
		GfxTracksTimeStep                       30.000
		GfxAreaNumXIntrPnts                     1
		GfxAreaNumYIntrPnts                     1
		CreateLaunchMTO                         No 
		CovarianceSigmaScale                    3.000
		CovarianceMode                          None 
    END ClsApp
    
    BEGIN Units
		DistanceUnit		Kilometers
		TimeUnit		Seconds
		DateFormat		GregorianUTC
		AngleUnit		Degrees
		MassUnit		Kilograms
		PowerUnit		dBW
		FrequencyUnit		Gigahertz
		SmallDistanceUnit		Meters
		LatitudeUnit		Degrees
		LongitudeUnit		Degrees
		DurationUnit		Hr:Min:Sec
		Temperature		Kelvin
		SmallTimeUnit		Seconds
		RatioUnit		Decibel
		RcsUnit		Decibel
		DopplerVelocityUnit		MetersperSecond
		SARTimeResProdUnit		Meter-Second
		ForceUnit		Newtons
		PressureUnit		Pascals
		SpecificImpulseUnit		Seconds
		PRFUnit		Kilohertz
		BandwidthUnit		Megahertz
		SmallVelocityUnit		CentimetersperSecond
		Percent		Percentage
		AviatorDistanceUnit		NauticalMiles
		AviatorTimeUnit		Hours
		AviatorAltitudeUnit		Feet
		AviatorFuelQuantityUnit		Pounds
		AviatorRunwayLengthUnit		Kilofeet
		AviatorBearingAngleUnit		Degrees
		AviatorAngleOfAttackUnit		Degrees
		AviatorAttitudeAngleUnit		Degrees
		AviatorGUnit		StandardSeaLevelG
		SolidAngle		Steradians
		AviatorTSFCUnit		TSFCLbmHrLbf
		AviatorPSFCUnit		PSFCLbmHrHp
		AviatorForceUnit		Pounds
		AviatorPowerUnit		Horsepower
		SpectralBandwidthUnit		Hertz
		AviatorAltTimeUnit		Minutes
		AviatorSmallTimeUnit		Seconds
		AviatorEnergyUnit		kilowatt-hours
		BitsUnit		MegaBits
		MagneticFieldUnit		nanoTesla
    END Units
    
    BEGIN ReportUnits
		DistanceUnit		Kilometers
		TimeUnit		Seconds
		DateFormat		GregorianUTC
		AngleUnit		Degrees
		MassUnit		Kilograms
		PowerUnit		dBW
		FrequencyUnit		Gigahertz
		SmallDistanceUnit		Meters
		LatitudeUnit		Degrees
		LongitudeUnit		Degrees
		DurationUnit		Hr:Min:Sec
		Temperature		Kelvin
		SmallTimeUnit		Seconds
		RatioUnit		Decibel
		RcsUnit		Decibel
		DopplerVelocityUnit		MetersperSecond
		SARTimeResProdUnit		Meter-Second
		ForceUnit		Newtons
		PressureUnit		Pascals
		SpecificImpulseUnit		Seconds
		PRFUnit		Kilohertz
		BandwidthUnit		Megahertz
		SmallVelocityUnit		CentimetersperSecond
		Percent		Percentage
		AviatorDistanceUnit		NauticalMiles
		AviatorTimeUnit		Hours
		AviatorAltitudeUnit		Feet
		AviatorFuelQuantityUnit		Pounds
		AviatorRunwayLengthUnit		Kilofeet
		AviatorBearingAngleUnit		Degrees
		AviatorAngleOfAttackUnit		Degrees
		AviatorAttitudeAngleUnit		Degrees
		AviatorGUnit		StandardSeaLevelG
		SolidAngle		Steradians
		AviatorTSFCUnit		TSFCLbmHrLbf
		AviatorPSFCUnit		PSFCLbmHrHp
		AviatorForceUnit		Pounds
		AviatorPowerUnit		Horsepower
		SpectralBandwidthUnit		Hertz
		AviatorAltTimeUnit		Minutes
		AviatorSmallTimeUnit		Seconds
		AviatorEnergyUnit		kilowatt-hours
		BitsUnit		MegaBits
		MagneticFieldUnit		nanoTesla
    END ReportUnits
    
    BEGIN ConnectReportUnits
		DistanceUnit		Kilometers
		TimeUnit		Seconds
		DateFormat		GregorianUTC
		AngleUnit		Degrees
		MassUnit		Kilograms
		PowerUnit		dBW
		FrequencyUnit		Gigahertz
		SmallDistanceUnit		Meters
		LatitudeUnit		Degrees
		LongitudeUnit		Degrees
		DurationUnit		Hr:Min:Sec
		Temperature		Kelvin
		SmallTimeUnit		Seconds
		RatioUnit		Decibel
		RcsUnit		Decibel
		DopplerVelocityUnit		MetersperSecond
		SARTimeResProdUnit		Meter-Second
		ForceUnit		Newtons
		PressureUnit		Pascals
		SpecificImpulseUnit		Seconds
		PRFUnit		Kilohertz
		BandwidthUnit		Megahertz
		SmallVelocityUnit		CentimetersperSecond
		Percent		Percentage
		AviatorDistanceUnit		NauticalMiles
		AviatorTimeUnit		Hours
		AviatorAltitudeUnit		Feet
		AviatorFuelQuantityUnit		Pounds
		AviatorRunwayLengthUnit		Kilofeet
		AviatorBearingAngleUnit		Degrees
		AviatorAngleOfAttackUnit		Degrees
		AviatorAttitudeAngleUnit		Degrees
		AviatorGUnit		StandardSeaLevelG
		SolidAngle		Steradians
		AviatorTSFCUnit		TSFCLbmHrLbf
		AviatorPSFCUnit		PSFCLbmHrHp
		AviatorForceUnit		Pounds
		AviatorPowerUnit		Horsepower
		SpectralBandwidthUnit		Hertz
		AviatorAltTimeUnit		Minutes
		AviatorSmallTimeUnit		Seconds
		AviatorEnergyUnit		kilowatt-hours
		BitsUnit		MegaBits
		MagneticFieldUnit		nanoTesla
    END ConnectReportUnits
    
    BEGIN ReportFavorites
        BEGIN Class
            Name  AdvCAT
            BEGIN Favorite
                Type    Report
                BaseDir Install
                Style   Close Approach By Min Sep
            END Favorite
        END Class
    END ReportFavorites
    
    BEGIN ADFFileData
    END ADFFileData
    
    BEGIN GenDb

		BEGIN Database
		    DbType       Satellite
		    DefDb        stkAllTLE.sd
		    UseMyDb      Off
		    MaxMatches   2000
		    Use4SOC      On

		BEGIN FieldDefaults

			BEGIN Field
				Name "SSC Number"
				Default "*"
			END Field

			BEGIN Field
				Name "Common Name"
				Default "*"
			END Field

		END FieldDefaults

		END Database

		BEGIN Database
		    DbType       City
		    DefDb        stkCityDb.cd
		    UseMyDb      Off
		    MaxMatches   2000
		    Use4SOC      On

		BEGIN FieldDefaults

			BEGIN Field
				Name "City Name"
				Default "*"
			END Field

		END FieldDefaults

		END Database

		BEGIN Database
		    DbType       Facility
		    DefDb        stkFacility.fd
		    UseMyDb      Off
		    MaxMatches   2000
		    Use4SOC      On

		BEGIN FieldDefaults

		END FieldDefaults

		END Database
    END GenDb
    
    BEGIN SOCDb
        BEGIN Defaults
            BEGIN Catalog Spacecraft
                BEGIN Criteria Name or ID
                    Type  Value
                    Value AISAT
                END Criteria
                BEGIN Criteria Periapsis Altitude
                    Type  Max
                    Max   2000 km
                END Criteria
                BEGIN Criteria Apoapsis Altitude
                    Type  Max
                    Max   2000 km
                END Criteria
            END Catalog
        END Defaults
    END SOCDb
    
    BEGIN Msgp4Ext
    END Msgp4Ext
    
    BEGIN FileLocations
    END FileLocations
    
    BEGIN Author
	Optimize	No
	UseBasicGlobe	No
	SaveEphemeris	Yes
	SaveScenFolder	No
	BEGIN ExternalFileTypes
	    BEGIN Type
		FileType  Calculation Scalar
		Include    Yes
	    END Type
	    BEGIN Type
		FileType  Celestial Image
		Include    No
	    END Type
	    BEGIN Type
		FileType  Cloud
		Include    Yes
	    END Type
	    BEGIN Type
		FileType  EOP
		Include    Yes
	    END Type
	    BEGIN Type
		FileType  External Vector Data
		Include    Yes
	    END Type
	    BEGIN Type
		FileType  Globe
		Include    Yes
	    END Type
	    BEGIN Type
		FileType  Globe Data
		Include    No
	    END Type
	    BEGIN Type
		FileType  Map
		Include    No
	    END Type
	    BEGIN Type
		FileType  Map Image
		Include    No
	    END Type
	    BEGIN Type
		FileType  Marker/Label
		Include    Yes
	    END Type
	    BEGIN Type
		FileType  Model
		Include    Yes
	    END Type
	    BEGIN Type
		FileType  Object Break-up File
		Include    No
	    END Type
	    BEGIN Type
		FileType  Planetary Ephemeris
		Include    No
	    END Type
	    BEGIN Type
		FileType  Report Style Script
		Include    Yes
	    END Type
	    BEGIN Type
		FileType  Report/Graph Style
		Include    Yes
	    END Type
	    BEGIN Type
		FileType  Scalar Calculation File
		Include    Yes
	    END Type
	    BEGIN Type
		FileType  Terrain
		Include    Yes
	    END Type
	    BEGIN Type
		FileType  Volume Grid Intervals File
		Include    Yes
	    END Type
	    BEGIN Type
		FileType  Volumetric File
		Include    Yes
	    END Type
	    BEGIN Type
		FileType  WTM
		Include    Yes
	    END Type
	END ExternalFileTypes
	ReadOnly	No
	ViewerPassword	No
	STKPassword	No
	ExcludeInstallFiles	No
	BEGIN ExternalFileList
	END ExternalFileList
    END Author
    
    BEGIN ExportDataFile
    FileType         Ephemeris
    IntervalType     Ephemeris
    TimePeriodStart  0.000000e+00
    TimePeriodStop   0.000000e+00
    StepType         Ephemeris
    StepSize         60.000000
    EphemType        STK
    UseVehicleCentralBody   Yes
    CentralBody      Earth
    SatelliteID      -200000
    CoordSys         ICRF
    NonSatCoordSys   Fixed
    InterpolateBoundaries  Yes
    EphemFormat      Current
    InterpType       9
    InterpOrder      5
    AttCoordSys      Fixed
    Quaternions      0
    ExportCovar      Position
    AttitudeFormat   Current
    TimePrecision      6
    CCSDSDateFormat    YMD
    CCSDSEphFormat     SciNotation
    CCSDSTimeSystem    UTC
    CCSDSRefFrame      ICRF
    UseSatCenterAndFrame   No
    IncludeCovariance      No
    IncludeAcceleration    No
    CCSDSFileFormat      KVN
    END ExportDataFile
    
    BEGIN Desc
    Begin ShortText

    End ShortText
    Begin LongText

    End LongText
    END Desc
    
    BEGIN RfEnv
<?xml version = "1.0" standalone = "yes"?>
<VAR name = "STK_RF_Environment">
    <SCOPE Class = "RFEnvironment">
        <VAR name = "Version">
            <STRING>&quot;1.0.0 a&quot;</STRING>
        </VAR>
        <VAR name = "ComponentName">
            <STRING>&quot;STK_RF_Environment&quot;</STRING>
        </VAR>
        <VAR name = "Description">
            <STRING>&quot;STK RF Environment&quot;</STRING>
        </VAR>
        <VAR name = "Type">
            <STRING>&quot;STK RF Environment&quot;</STRING>
        </VAR>
        <VAR name = "UserComment">
            <STRING>&quot;STK RF Environment&quot;</STRING>
        </VAR>
        <VAR name = "ReadOnly">
            <BOOL>false</BOOL>
        </VAR>
        <VAR name = "Clonable">
            <BOOL>true</BOOL>
        </VAR>
        <VAR name = "Category">
            <STRING>&quot;&quot;</STRING>
        </VAR>
        <VAR name = "PropagationChannel">
            <VAR name = "RF_Propagation_Channel">
                <SCOPE Class = "PropagationChannel">
                    <VAR name = "Version">
                        <STRING>&quot;1.0.0 a&quot;</STRING>
                    </VAR>
                    <VAR name = "ComponentName">
                        <STRING>&quot;RF_Propagation_Channel&quot;</STRING>
                    </VAR>
                    <VAR name = "Description">
                        <STRING>&quot;RF Propagation Channel&quot;</STRING>
                    </VAR>
                    <VAR name = "Type">
                        <STRING>&quot;RF Propagation Channel&quot;</STRING>
                    </VAR>
                    <VAR name = "UserComment">
                        <STRING>&quot;RF Propagation Channel&quot;</STRING>
                    </VAR>
                    <VAR name = "ReadOnly">
                        <BOOL>false</BOOL>
                    </VAR>
                    <VAR name = "Clonable">
                        <BOOL>true</BOOL>
                    </VAR>
                    <VAR name = "Category">
                        <STRING>&quot;&quot;</STRING>
                    </VAR>
                    <VAR name = "UseITU618Section2p5">
                        <BOOL>false</BOOL>
                    </VAR>
                    <VAR name = "UseCloudFogModel">
                        <BOOL>false</BOOL>
                    </VAR>
                    <VAR name = "CloudFogModel">
                        <VAR name = "ITU-R_P840-6">
                            <SCOPE Class = "CloudFogLossModel">
                                <VAR name = "Version">
                                    <STRING>&quot;1.0.0 a&quot;</STRING>
                                </VAR>
                                <VAR name = "ComponentName">
                                    <STRING>&quot;ITU-R_P840-6&quot;</STRING>
                                </VAR>
                                <VAR name = "Description">
                                    <STRING>&quot;ITU-R P840-6&quot;</STRING>
                                </VAR>
                                <VAR name = "Type">
                                    <STRING>&quot;ITU-R P840-6&quot;</STRING>
                                </VAR>
                                <VAR name = "UserComment">
                                    <STRING>&quot;ITU-R P840-6&quot;</STRING>
                                </VAR>
                                <VAR name = "ReadOnly">
                                    <BOOL>false</BOOL>
                                </VAR>
                                <VAR name = "Clonable">
                                    <BOOL>true</BOOL>
                                </VAR>
                                <VAR name = "Category">
                                    <STRING>&quot;&quot;</STRING>
                                </VAR>
                                <VAR name = "LiquidWaterDensityValueChoice">
                                    <STRING>&quot;Liquid Water Content Density Value&quot;</STRING>
                                </VAR>
                                <VAR name = "CloudCeiling">
                                    <QUANTITY Dimension = "DistanceUnit" Unit = "m">
                                        <REAL>3000</REAL>
                                    </QUANTITY>
                                </VAR>
                                <VAR name = "CloudLayerThickness">
                                    <QUANTITY Dimension = "DistanceUnit" Unit = "m">
                                        <REAL>500</REAL>
                                    </QUANTITY>
                                </VAR>
                                <VAR name = "CloudTemp">
                                    <QUANTITY Dimension = "Temperature" Unit = "K">
                                        <REAL>273.15</REAL>
                                    </QUANTITY>
                                </VAR>
                                <VAR name = "CloudLiqWaterDensity">
                                    <QUANTITY Dimension = "SmallDensity" Unit = "kg*m^-3">
                                        <REAL>0.0075</REAL>
                                    </QUANTITY>
                                </VAR>
                                <VAR name = "AnnualAveragePercentValue">
                                    <QUANTITY Dimension = "Percent" Unit = "unitValue">
                                        <REAL>0.01</REAL>
                                    </QUANTITY>
                                </VAR>
                                <VAR name = "MonthlyAveragePercentValue">
                                    <QUANTITY Dimension = "Percent" Unit = "unitValue">
                                        <REAL>0.01</REAL>
                                    </QUANTITY>
                                </VAR>
                                <VAR name = "LiqWaterAverageDataMonth">
                                    <INT>1</INT>
                                </VAR>
                            </SCOPE>
                        </VAR>
                    </VAR>
                    <VAR name = "UseTropoScintModel">
                        <BOOL>false</BOOL>
                    </VAR>
                    <VAR name = "TropoScintModel">
                        <VAR name = "ITU-R_P618-12">
                            <SCOPE Class = "TropoScintLossModel">
                                <VAR name = "Version">
                                    <STRING>&quot;1.0.0 a&quot;</STRING>
                                </VAR>
                                <VAR name = "ComponentName">
                                    <STRING>&quot;ITU-R_P618-12&quot;</STRING>
                                </VAR>
                                <VAR name = "Description">
                                    <STRING>&quot;ITU-R P618-12&quot;</STRING>
                                </VAR>
                                <VAR name = "Type">
                                    <STRING>&quot;ITU-R P618-12&quot;</STRING>
                                </VAR>
                                <VAR name = "UserComment">
                                    <STRING>&quot;ITU-R P618-12&quot;</STRING>
                                </VAR>
                                <VAR name = "ReadOnly">
                                    <BOOL>false</BOOL>
                                </VAR>
                                <VAR name = "Clonable">
                                    <BOOL>true</BOOL>
                                </VAR>
                                <VAR name = "Category">
                                    <STRING>&quot;&quot;</STRING>
                                </VAR>
                                <VAR name = "FadeDepthAverageTimeChoice">
                                    <STRING>&quot;Fade depth for the average year&quot;</STRING>
                                </VAR>
                                <VAR name = "ComputeDeepFade">
                                    <BOOL>false</BOOL>
                                </VAR>
                                <VAR name = "FadeOutage">
                                    <QUANTITY Dimension = "Percent" Unit = "unitValue">
                                        <REAL>0.001</REAL>
                                    </QUANTITY>
                                </VAR>
                                <VAR name = "PercentTimeRefracGrad">
                                    <QUANTITY Dimension = "Percent" Unit = "unitValue">
                                        <REAL>0.1</REAL>
                                    </QUANTITY>
                                </VAR>
                                <VAR name = "SurfaceTemperature">
                                    <QUANTITY Dimension = "Temperature" Unit = "K">
                                        <REAL>273.15</REAL>
                                    </QUANTITY>
                                </VAR>
                            </SCOPE>
                        </VAR>
                    </VAR>
                    <VAR name = "UseRainModel">
                        <BOOL>false</BOOL>
                    </VAR>
                    <VAR name = "RainModel">
                        <VAR name = "ITU-R_P618-12">
                            <SCOPE Class = "RainLossModel">
                                <VAR name = "Version">
                                    <STRING>&quot;1.0.0 a&quot;</STRING>
                                </VAR>
                                <VAR name = "ComponentName">
                                    <STRING>&quot;ITU-R_P618-12&quot;</STRING>
                                </VAR>
                                <VAR name = "Description">
                                    <STRING>&quot;ITU-R P618-12 rain model&quot;</STRING>
                                </VAR>
                                <VAR name = "Type">
                                    <STRING>&quot;ITU-R P618-12&quot;</STRING>
                                </VAR>
                                <VAR name = "UserComment">
                                    <STRING>&quot;ITU-R P618-12 rain model&quot;</STRING>
                                </VAR>
                                <VAR name = "ReadOnly">
                                    <BOOL>false</BOOL>
                                </VAR>
                                <VAR name = "Clonable">
                                    <BOOL>true</BOOL>
                                </VAR>
                                <VAR name = "Category">
                                    <STRING>&quot;&quot;</STRING>
                                </VAR>
                                <VAR name = "SurfaceTemperature">
                                    <QUANTITY Dimension = "Temperature" Unit = "K">
                                        <REAL>273.15</REAL>
                                    </QUANTITY>
                                </VAR>
                                <VAR name = "EnableDepolarizationLoss">
                                    <BOOL>false</BOOL>
                                </VAR>
                            </SCOPE>
                        </VAR>
                    </VAR>
                    <VAR name = "UseAtmosAbsorptionModel">
                        <BOOL>false</BOOL>
                    </VAR>
                    <VAR name = "AtmosAbsorptionModel">
                        <VAR name = "Simple_Satcom">
                            <SCOPE Class = "AtmosphericAbsorptionModel">
                                <VAR name = "Version">
                                    <STRING>&quot;1.0.1 a&quot;</STRING>
                                </VAR>
                                <VAR name = "ComponentName">
                                    <STRING>&quot;Simple_Satcom&quot;</STRING>
                                </VAR>
                                <VAR name = "Description">
                                    <STRING>&quot;Simple Satcom gaseous absorption model&quot;</STRING>
                                </VAR>
                                <VAR name = "Type">
                                    <STRING>&quot;Simple Satcom&quot;</STRING>
                                </VAR>
                                <VAR name = "UserComment">
                                    <STRING>&quot;Simple Satcom gaseous absorption model&quot;</STRING>
                                </VAR>
                                <VAR name = "ReadOnly">
                                    <BOOL>false</BOOL>
                                </VAR>
                                <VAR name = "Clonable">
                                    <BOOL>true</BOOL>
                                </VAR>
                                <VAR name = "Category">
                                    <STRING>&quot;&quot;</STRING>
                                </VAR>
                                <VAR name = "SurfaceTemperature">
                                    <QUANTITY Dimension = "Temperature" Unit = "K">
                                        <REAL>293.15</REAL>
                                    </QUANTITY>
                                </VAR>
                                <VAR name = "WaterVaporConcentration">
                                    <QUANTITY Dimension = "Density" Unit = "g*m^-3">
                                        <REAL>7.5</REAL>
                                    </QUANTITY>
                                </VAR>
                            </SCOPE>
                        </VAR>
                    </VAR>
                    <VAR name = "UseUrbanTerresPropLossModel">
                        <BOOL>false</BOOL>
                    </VAR>
                    <VAR name = "UrbanTerresPropLossModel">
                        <VAR name = "Two_Ray">
                            <SCOPE Class = "UrbanTerrestrialPropagationLossModel">
                                <VAR name = "Version">
                                    <STRING>&quot;1.0.0 a&quot;</STRING>
                                </VAR>
                                <VAR name = "ComponentName">
                                    <STRING>&quot;Two_Ray&quot;</STRING>
                                </VAR>
                                <VAR name = "Description">
                                    <STRING>&quot;Two Ray (Fourth Power Law) atmospheric absorption model&quot;</STRING>
                                </VAR>
                                <VAR name = "Type">
                                    <STRING>&quot;Two Ray&quot;</STRING>
                                </VAR>
                                <VAR name = "UserComment">
                                    <STRING>&quot;Two Ray (Fourth Power Law) atmospheric absorption model&quot;</STRING>
                                </VAR>
                                <VAR name = "ReadOnly">
                                    <BOOL>false</BOOL>
                                </VAR>
                                <VAR name = "Clonable">
                                    <BOOL>true</BOOL>
                                </VAR>
                                <VAR name = "Category">
                                    <STRING>&quot;&quot;</STRING>
                                </VAR>
                                <VAR name = "SurfaceTemperature">
                                    <QUANTITY Dimension = "Temperature" Unit = "K">
                                        <REAL>273.15</REAL>
                                    </QUANTITY>
                                </VAR>
                                <VAR name = "LossFactor">
                                    <REAL>1</REAL>
                                </VAR>
                            </SCOPE>
                        </VAR>
                    </VAR>
                    <VAR name = "UseCustomA">
                        <BOOL>false</BOOL>
                    </VAR>
                    <VAR name = "UseCustomB">
                        <BOOL>false</BOOL>
                    </VAR>
                    <VAR name = "UseCustomC">
                        <BOOL>false</BOOL>
                    </VAR>
                </SCOPE>
            </VAR>
        </VAR>
        <VAR name = "EarthTemperature">
            <QUANTITY Dimension = "Temperature" Unit = "K">
                <REAL>290</REAL>
            </QUANTITY>
        </VAR>
        <VAR name = "RainOutagePercent">
            <PROP name = "FormatString">
                <STRING>&quot;%#6.3f&quot;</STRING>
            </PROP>
            <REAL>0.1</REAL>
        </VAR>
        <VAR name = "ActiveCommSystem">
            <LINKTOOBJ>
                <STRING>&quot;None&quot;</STRING>
            </LINKTOOBJ>
        </VAR>
    </SCOPE>
</VAR>    END RfEnv
    
    BEGIN CommRad
    END CommRad
    
    BEGIN RadarCrossSection
<?xml version = "1.0" standalone = "yes"?>
<VAR name = "STK_Radar_RCS_Extension">
    <SCOPE Class = "RadarRCSExtension">
        <VAR name = "Version">
            <STRING>&quot;1.0.0 a&quot;</STRING>
        </VAR>
        <VAR name = "ComponentName">
            <STRING>&quot;STK_Radar_RCS_Extension&quot;</STRING>
        </VAR>
        <VAR name = "Description">
            <STRING>&quot;STK Radar RCS Extension&quot;</STRING>
        </VAR>
        <VAR name = "Type">
            <STRING>&quot;STK Radar RCS Extension&quot;</STRING>
        </VAR>
        <VAR name = "UserComment">
            <STRING>&quot;STK Radar RCS Extension&quot;</STRING>
        </VAR>
        <VAR name = "ReadOnly">
            <BOOL>false</BOOL>
        </VAR>
        <VAR name = "Clonable">
            <BOOL>true</BOOL>
        </VAR>
        <VAR name = "Category">
            <STRING>&quot;&quot;</STRING>
        </VAR>
        <VAR name = "Model">
            <VAR name = "Radar_Cross_Section">
                <SCOPE Class = "RCS">
                    <VAR name = "Version">
                        <STRING>&quot;1.0.0 a&quot;</STRING>
                    </VAR>
                    <VAR name = "ComponentName">
                        <STRING>&quot;Radar_Cross_Section&quot;</STRING>
                    </VAR>
                    <VAR name = "Description">
                        <STRING>&quot;Radar Cross Section&quot;</STRING>
                    </VAR>
                    <VAR name = "Type">
                        <STRING>&quot;Radar Cross Section&quot;</STRING>
                    </VAR>
                    <VAR name = "UserComment">
                        <STRING>&quot;Radar Cross Section&quot;</STRING>
                    </VAR>
                    <VAR name = "ReadOnly">
                        <BOOL>false</BOOL>
                    </VAR>
                    <VAR name = "Clonable">
                        <BOOL>true</BOOL>
                    </VAR>
                    <VAR name = "Category">
                        <STRING>&quot;&quot;</STRING>
                    </VAR>
                    <VAR name = "FrequencyBandList">
                        <LIST>
                            <SCOPE>
                                <VAR name = "MinFrequency">
                                    <QUANTITY Dimension = "BandwidthUnit" Unit = "Hz">
                                        <REAL>2997920</REAL>
                                    </QUANTITY>
                                </VAR>
                                <VAR name = "ComputeTypeStrategy">
                                    <VAR name = "Constant Value">
                                        <SCOPE Class = "RCS Compute Strategy">
                                            <VAR name = "ConstantValue">
                                                <QUANTITY Dimension = "RcsUnit" Unit = "sqm">
                                                    <REAL>1</REAL>
                                                </QUANTITY>
                                            </VAR>
                                            <VAR name = "Type">
                                                <STRING>&quot;Constant Value&quot;</STRING>
                                            </VAR>
                                            <VAR name = "ComponentName">
                                                <STRING>&quot;Constant Value&quot;</STRING>
                                            </VAR>
                                        </SCOPE>
                                    </VAR>
                                </VAR>
                                <VAR name = "SwerlingCase">
                                    <STRING>&quot;0&quot;</STRING>
                                </VAR>
                            </SCOPE>
                        </LIST>
                    </VAR>
                </SCOPE>
            </VAR>
        </VAR>
    </SCOPE>
</VAR>    END RadarCrossSection
    
    BEGIN RadarClutter
<?xml version = "1.0" standalone = "yes"?>
<VAR name = "STK_Radar_Clutter_Extension">
    <SCOPE Class = "RadarClutterExtension">
        <VAR name = "Version">
            <STRING>&quot;1.0.0 a&quot;</STRING>
        </VAR>
        <VAR name = "ComponentName">
            <STRING>&quot;STK_Radar_Clutter_Extension&quot;</STRING>
        </VAR>
        <VAR name = "Description">
            <STRING>&quot;STK Radar Clutter Extension&quot;</STRING>
        </VAR>
        <VAR name = "Type">
            <STRING>&quot;STK Radar Clutter Extension&quot;</STRING>
        </VAR>
        <VAR name = "UserComment">
            <STRING>&quot;STK Radar Clutter Extension&quot;</STRING>
        </VAR>
        <VAR name = "ReadOnly">
            <BOOL>false</BOOL>
        </VAR>
        <VAR name = "Clonable">
            <BOOL>true</BOOL>
        </VAR>
        <VAR name = "Category">
            <STRING>&quot;&quot;</STRING>
        </VAR>
        <VAR name = "ClutterMap">
            <VAR name = "Constant Coefficient">
                <SCOPE Class = "Clutter Map">
                    <VAR name = "ClutterCoefficient">
                        <QUANTITY Dimension = "RatioUnit" Unit = "units">
                            <REAL>1</REAL>
                        </QUANTITY>
                    </VAR>
                    <VAR name = "Type">
                        <STRING>&quot;Constant Coefficient&quot;</STRING>
                    </VAR>
                    <VAR name = "ComponentName">
                        <STRING>&quot;Constant Coefficient&quot;</STRING>
                    </VAR>
                </SCOPE>
            </VAR>
        </VAR>
    </SCOPE>
</VAR>    END RadarClutter
    
    BEGIN Gator
    END Gator
    
    BEGIN Crdn
    END Crdn
    
    BEGIN SpiceExt
    END SpiceExt
    
    BEGIN FlightScenExt
    END FlightScenExt
    
    BEGIN Graphics

BEGIN Animation

    StartTime          10 Jul 2026 06:30:00.000000000
    EndTime            11 Jul 2026 06:30:00.000000000
    CurrentTime        10 Jul 2026 07:45:53.403954223
    Direction          Reverse
    UpdateDelta        0.100000
    RefreshDelta       0.010000
    XRealTimeMult      1.000000
    RealTimeOffset     0.000000
    XRtStartFromPause  Yes

END Animation


        BEGIN DisplayFlags
            ShowLabels           On
            ShowPassLabel        Off
            ShowElsetNum         Off
            ShowGndTracks        On
            ShowGndMarkers       On
            ShowOrbitMarkers     On
            ShowPlanetOrbits     Off
            ShowPlanetCBIPos     On
            ShowPlanetCBILabel   On
            ShowPlanetGndPos     On
            ShowPlanetGndLabel   On
            ShowSensors          On
            ShowWayptMarkers     Off
            ShowWayptTurnMarkers Off
            ShowOrbits           On
            ShowDtedRegions      Off
            ShowAreaTgtCentroids On
            ShowToolBar          On
            ShowStatusBar        On
            ShowScrollBars       On
            AllowAnimUpdate      On
            AccShowLine          On
            AccAnimHigh          On
            AccStatHigh          On
            ShowPrintButton      On
            ShowAnimButtons      On
            ShowAnimModeButtons  On
            ShowZoomMsrButtons   On
            ShowMapCbButton      Off
        END DisplayFlags

BEGIN WinFonts

    System
    MS Sans Serif,22,0,0
    MS Sans Serif,28,0,0

END WinFonts

BEGIN MapData

    Begin TerrainConverterData
           NorthLat        0.00000000000000e+00
           EastLon         0.00000000000000e+00
           SouthLat        0.00000000000000e+00
           WestLon         0.00000000000000e+00
           ColorByRGB      No
           AltsFromMSL     No
           UseColorRamp    Yes
           UseRegionMinMax Yes
           SizeSameAsSrc   Yes
           MinAltHSV       0.00000000000000e+00 7.00000000000000e-01 8.00000000000000e-01 4.00000000000000e-01
           MaxAltHSV       1.00000000000000e+06 0.00000000000000e+00 2.00000000000000e-01 1.00000000000000e+00
           SmoothColors    Yes
           CreateChunkTrn  No
           OutputFormat    PDTTX
    End TerrainConverterData

    DisableDefKbdActions     Off
    TextShadowStyle          Dark
    TextShadowColor          #000000
    BingLevelOfDetailScale   2.000000
    BEGIN Map
        MapNum         1
        TrackingMode   LatLon
        PickEnabled    On
        PanEnabled     On

        BEGIN MapAttributes
            PrimaryBody          Earth
            SecondaryBody        Sun
            CenterLatitude       0.000000
            CenterLongitude      0.000000
            ProjectionAltitude   63621860.000000
            FieldOfView          35.000000
            OrthoDisplayDistance 20000000.000000
            TransformTrajectory  On
            EquatorialRadius     6378137.000000
            BackgroundColor      #000000
            LatLonLines          On
            LatSpacing           30.000000
            LonSpacing           30.000000
            LatLonLineColor      #999999
            LatLonLineStyle      2
            ShowOrthoDistGrid    Off
            OrthoGridXSpacing    5
            OrthoGridYSpacing    5
            OrthoGridColor       #ffffff
            ShowImageExtents     Off
            ImageExtentLineColor #ffffff
            ImageExtentLineStyle 0
            ImageExtentLineWidth 1.000000
            ShowImageNames       Off
            ImageNameFont        0
            Projection           EquidistantCylindrical
            Resolution           VeryLow
            CoordinateSys        ECF
            UseBackgroundImage   On
            UseBingForBackground Off
            BingType             Aerial
            BingLogoHorizAlign   Right
            BingLogoVertAlign    Bottom
            BackgroundImageFile  Basic.bmp
            UseNightLights       Off
            NightLightsFactor    3.500000
            UseCloudsFile        Off
            BEGIN ZoomLocations
                BEGIN ZoomLocation
                    CenterLat    0.000000
                    CenterLon    0.000000
                    ZoomWidth    360.000000
                    ZoomHeight   180.000000
                End ZoomLocation
            END ZoomLocations
            UseVarAspectRatio    No
            SwapMapResolution    Yes
            NoneToVLowSwapDist   2000000.000000
            VLowToLowSwapDist    20000.000000
            LowToMediumSwapDist  10000.000000
            MediumToHighSwapDist 5000.000000
            HighToVHighSwapDist  1000.000000
            VHighToSHighSwapDist 100.000000
            BEGIN Axes
                DisplayAxes no
                CoordSys    CBI
                2aryCB      Sun
                Display+x   yes
                Label+x     yes
                Color+x     #ffffff
                Scale+x     3.000000
                Display-x   yes
                Label-x     yes
                Color-x     #ffffff
                Scale-x     3.000000
                Display+y   yes
                Label+y     yes
                Color+y     #ffffff
                Scale+y     3.000000
                Display-y   yes
                Label-y     yes
                Color-y     #ffffff
                Scale-y     3.000000
                Display+z   yes
                Label+z     yes
                Color+z     #ffffff
                Scale+z     3.000000
                Display-z   yes
                Label-z     yes
                Color-z     #ffffff
                Scale-z     3.000000
            END Axes

        END MapAttributes

        BEGIN MapList
            BEGIN Detail
                Alias RWDB2_Coastlines
                Show Yes
                Color #8fbc8f
            END Detail
            BEGIN Detail
                Alias RWDB2_International_Borders
                Show No
                Color #8fbc8f
            END Detail
            BEGIN Detail
                Alias RWDB2_Islands
                Show No
                Color #8fbc8f
            END Detail
            BEGIN Detail
                Alias RWDB2_Lakes
                Show No
                Color #87cefa
            END Detail
            BEGIN Detail
                Alias RWDB2_Provincial_Borders
                Show No
                Color #8fbc8f
            END Detail
            BEGIN Detail
                Alias RWDB2_Rivers
                Show No
                Color #87cefa
            END Detail
        END MapList


        BEGIN MapAnnotations
        END MapAnnotations

        BEGIN DisplayFlags
            ShowLabels           On
            ShowPassLabel        Off
            ShowElsetNum         Off
            ShowGndTracks        On
            ShowGndMarkers       On
            ShowOrbitMarkers     On
            ShowPlanetOrbits     Off
            ShowPlanetCBIPos     On
            ShowPlanetCBILabel   On
            ShowPlanetGndPos     On
            ShowPlanetGndLabel   On
            ShowSensors          On
            ShowWayptMarkers     Off
            ShowWayptTurnMarkers Off
            ShowOrbits           On
            ShowDtedRegions      Off
            ShowAreaTgtCentroids On
            ShowToolBar          On
            ShowStatusBar        On
            ShowScrollBars       On
            AllowAnimUpdate      Off
            AccShowLine          On
            AccAnimHigh          On
            AccStatHigh          On
            ShowPrintButton      On
            ShowAnimButtons      On
            ShowAnimModeButtons  On
            ShowZoomMsrButtons   On
            ShowMapCbButton      Off
        END DisplayFlags

        BEGIN SoftVTR
            OutputFormat     WMV
            Directory        C:\Users\sammy\OneDrive\Documents\STK 11 (x64)\Hope_2
            BaseName         Frame
            Digits           4
            Frame            0
            LastAnimTime     0.000000
            OutputMode       Normal
            HiResAssembly    Assemble
            HRWidth          6000
            HRHeight         4500
            HRDPI            600.000000
            UseSnapInterval  No
            SnapInterval     0.000000
            WmvCodec         "Windows Media Video 9"
            Framerate        30
            Bitrate          3000000
        END SoftVTR


        BEGIN TimeDisplay
            Show             0
            TextColor        #ffffff
            TextTranslucency 0.000000
            ShowBackground   0
            BackColor        #4d4d4d
            BackTranslucency 0.400000
            XPosition        20
            YPosition        -20
        END TimeDisplay

        BEGIN LightingData
            DisplayAltitude              0.000000
            SubsolarPoint                Off
            SubsolarPointColor           #ffff00
            SubsolarPointMarkerStyle     2

            ShowUmbraLine                Off
            UmbraLineColor               #000000
            UmbraLineStyle               0
            UmbraLineWidth               2
            FillUmbra                    On
            UmbraFillColor               #000000
            ShowSunlightLine             Off
            SunlightLineColor            #ffff00
            SunlightLineStyle            0
            SunlightLineWidth            2
            FillSunlight                 On
            SunlightFillColor            #ffffff
            SunlightMinOpacity           0.000000
            SunlightMaxOpacity           0.200000
            UmbraMaxOpacity              0.700000
            UmbraMinOpacity              0.400000
        END LightingData
    END Map

    BEGIN MapStyles

        UseStyleTime        No

        BEGIN Style
        Name                DefaultWithBing
        Time                0.000000
        UpdateDelta         10.000000

        BEGIN MapAttributes
            PrimaryBody          Earth
            SecondaryBody        Sun
            CenterLatitude       0.000000
            CenterLongitude      0.000000
            ProjectionAltitude   63621860.000000
            FieldOfView          35.000000
            OrthoDisplayDistance 20000000.000000
            TransformTrajectory  On
            EquatorialRadius     6378137.000000
            BackgroundColor      #000000
            LatLonLines          On
            LatSpacing           30.000000
            LonSpacing           30.000000
            LatLonLineColor      #999999
            LatLonLineStyle      2
            ShowOrthoDistGrid    Off
            OrthoGridXSpacing    5
            OrthoGridYSpacing    5
            OrthoGridColor       #ffffff
            ShowImageExtents     Off
            ImageExtentLineColor #ffffff
            ImageExtentLineStyle 0
            ImageExtentLineWidth 1.000000
            ShowImageNames       Off
            ImageNameFont        0
            Projection           EquidistantCylindrical
            Resolution           VeryLow
            CoordinateSys        ECF
            UseBackgroundImage   On
            UseBingForBackground Off
            BingType             Aerial
            BingLogoHorizAlign   Right
            BingLogoVertAlign    Bottom
            BackgroundImageFile  Basic.bmp
            UseNightLights       Off
            NightLightsFactor    3.500000
            UseCloudsFile        Off
            BEGIN ZoomLocations
                BEGIN ZoomLocation
                    CenterLat    0.000000
                    CenterLon    0.000000
                    ZoomWidth    359.999998
                    ZoomHeight   180.000000
                End ZoomLocation
            END ZoomLocations
            UseVarAspectRatio    No
            SwapMapResolution    Yes
            NoneToVLowSwapDist   2000000.000000
            VLowToLowSwapDist    20000.000000
            LowToMediumSwapDist  10000.000000
            MediumToHighSwapDist 5000.000000
            HighToVHighSwapDist  1000.000000
            VHighToSHighSwapDist 100.000000
            BEGIN Axes
                DisplayAxes no
                CoordSys    CBI
                2aryCB      Sun
                Display+x   yes
                Label+x     yes
                Color+x     #ffffff
                Scale+x     3.000000
                Display-x   yes
                Label-x     yes
                Color-x     #ffffff
                Scale-x     3.000000
                Display+y   yes
                Label+y     yes
                Color+y     #ffffff
                Scale+y     3.000000
                Display-y   yes
                Label-y     yes
                Color-y     #ffffff
                Scale-y     3.000000
                Display+z   yes
                Label+z     yes
                Color+z     #ffffff
                Scale+z     3.000000
                Display-z   yes
                Label-z     yes
                Color-z     #ffffff
                Scale-z     3.000000
            END Axes

        END MapAttributes

        BEGIN MapList
            BEGIN Detail
                Alias RWDB2_Coastlines
                Show Yes
                Color #8fbc8f
            END Detail
            BEGIN Detail
                Alias RWDB2_International_Borders
                Show No
                Color #8fbc8f
            END Detail
            BEGIN Detail
                Alias RWDB2_Islands
                Show No
                Color #8fbc8f
            END Detail
            BEGIN Detail
                Alias RWDB2_Lakes
                Show No
                Color #87cefa
            END Detail
            BEGIN Detail
                Alias RWDB2_Provincial_Borders
                Show No
                Color #8fbc8f
            END Detail
            BEGIN Detail
                Alias RWDB2_Rivers
                Show No
                Color #87cefa
            END Detail
        END MapList


        BEGIN MapAnnotations
        END MapAnnotations

        BEGIN SoftVTR
            OutputFormat     WMV
            Directory        C:\Users\sammy\OneDrive\Documents\STK 11 (x64)\Hope_2
            BaseName         Frame
            Digits           4
            Frame            0
            LastAnimTime     0.000000
            OutputMode       Normal
            HiResAssembly    Assemble
            HRWidth          6000
            HRHeight         4500
            HRDPI            600.000000
            UseSnapInterval  No
            SnapInterval     0.000000
            WmvCodec         "Windows Media Video 9"
            Framerate        30
            Bitrate          3000000
        END SoftVTR


        BEGIN TimeDisplay
            Show             0
            TextColor        #ffffff
            TextTranslucency 0.000000
            ShowBackground   0
            BackColor        #4d4d4d
            BackTranslucency 0.400000
            XPosition        20
            YPosition        -20
        END TimeDisplay

        BEGIN LightingData
            DisplayAltitude              0.000000
            SubsolarPoint                Off
            SubsolarPointColor           #ffff00
            SubsolarPointMarkerStyle     2

            ShowUmbraLine                Off
            UmbraLineColor               #000000
            UmbraLineStyle               0
            UmbraLineWidth               2
            FillUmbra                    On
            UmbraFillColor               #000000
            ShowSunlightLine             Off
            SunlightLineColor            #ffff00
            SunlightLineStyle            0
            SunlightLineWidth            2
            FillSunlight                 On
            SunlightFillColor            #ffffff
            SunlightMinOpacity           0.000000
            SunlightMaxOpacity           0.200000
            UmbraMaxOpacity              0.700000
            UmbraMinOpacity              0.400000
        END LightingData

        ShowDtedRegions     Off

        End Style

        BEGIN Style
        Name                DefaultWithoutBing
        Time                0.000000
        UpdateDelta         10.000000

        BEGIN MapAttributes
            PrimaryBody          Earth
            SecondaryBody        Sun
            CenterLatitude       0.000000
            CenterLongitude      0.000000
            ProjectionAltitude   63621860.000000
            FieldOfView          35.000000
            OrthoDisplayDistance 20000000.000000
            TransformTrajectory  On
            EquatorialRadius     6378137.000000
            BackgroundColor      #000000
            LatLonLines          On
            LatSpacing           30.000000
            LonSpacing           30.000000
            LatLonLineColor      #999999
            LatLonLineStyle      2
            ShowOrthoDistGrid    Off
            OrthoGridXSpacing    5
            OrthoGridYSpacing    5
            OrthoGridColor       #ffffff
            ShowImageExtents     Off
            ImageExtentLineColor #ffffff
            ImageExtentLineStyle 0
            ImageExtentLineWidth 1.000000
            ShowImageNames       Off
            ImageNameFont        0
            Projection           EquidistantCylindrical
            Resolution           VeryLow
            CoordinateSys        ECF
            UseBackgroundImage   On
            UseBingForBackground Off
            BingType             Aerial
            BingLogoHorizAlign   Right
            BingLogoVertAlign    Bottom
            BackgroundImageFile  Basic.bmp
            UseNightLights       Off
            NightLightsFactor    3.500000
            UseCloudsFile        Off
            BEGIN ZoomLocations
                BEGIN ZoomLocation
                    CenterLat    0.000000
                    CenterLon    0.000000
                    ZoomWidth    359.999998
                    ZoomHeight   180.000000
                End ZoomLocation
            END ZoomLocations
            UseVarAspectRatio    No
            SwapMapResolution    Yes
            NoneToVLowSwapDist   2000000.000000
            VLowToLowSwapDist    20000.000000
            LowToMediumSwapDist  10000.000000
            MediumToHighSwapDist 5000.000000
            HighToVHighSwapDist  1000.000000
            VHighToSHighSwapDist 100.000000
            BEGIN Axes
                DisplayAxes no
                CoordSys    CBI
                2aryCB      Sun
                Display+x   yes
                Label+x     yes
                Color+x     #ffffff
                Scale+x     3.000000
                Display-x   yes
                Label-x     yes
                Color-x     #ffffff
                Scale-x     3.000000
                Display+y   yes
                Label+y     yes
                Color+y     #ffffff
                Scale+y     3.000000
                Display-y   yes
                Label-y     yes
                Color-y     #ffffff
                Scale-y     3.000000
                Display+z   yes
                Label+z     yes
                Color+z     #ffffff
                Scale+z     3.000000
                Display-z   yes
                Label-z     yes
                Color-z     #ffffff
                Scale-z     3.000000
            END Axes

        END MapAttributes

        BEGIN MapList
            BEGIN Detail
                Alias RWDB2_Coastlines
                Show Yes
                Color #8fbc8f
            END Detail
            BEGIN Detail
                Alias RWDB2_International_Borders
                Show No
                Color #8fbc8f
            END Detail
            BEGIN Detail
                Alias RWDB2_Islands
                Show No
                Color #8fbc8f
            END Detail
            BEGIN Detail
                Alias RWDB2_Lakes
                Show No
                Color #87cefa
            END Detail
            BEGIN Detail
                Alias RWDB2_Provincial_Borders
                Show No
                Color #8fbc8f
            END Detail
            BEGIN Detail
                Alias RWDB2_Rivers
                Show No
                Color #87cefa
            END Detail
        END MapList


        BEGIN MapAnnotations
        END MapAnnotations

        BEGIN SoftVTR
            OutputFormat     WMV
            Directory        C:\Users\sammy\OneDrive\Documents\STK 11 (x64)\Hope_2
            BaseName         Frame
            Digits           4
            Frame            0
            LastAnimTime     0.000000
            OutputMode       Normal
            HiResAssembly    Assemble
            HRWidth          6000
            HRHeight         4500
            HRDPI            600.000000
            UseSnapInterval  No
            SnapInterval     0.000000
            WmvCodec         "Windows Media Video 9"
            Framerate        30
            Bitrate          3000000
        END SoftVTR


        BEGIN TimeDisplay
            Show             0
            TextColor        #ffffff
            TextTranslucency 0.000000
            ShowBackground   0
            BackColor        #4d4d4d
            BackTranslucency 0.400000
            XPosition        20
            YPosition        -20
        END TimeDisplay

        BEGIN LightingData
            DisplayAltitude              0.000000
            SubsolarPoint                Off
            SubsolarPointColor           #ffff00
            SubsolarPointMarkerStyle     2

            ShowUmbraLine                Off
            UmbraLineColor               #000000
            UmbraLineStyle               0
            UmbraLineWidth               2
            FillUmbra                    On
            UmbraFillColor               #000000
            ShowSunlightLine             Off
            SunlightLineColor            #ffff00
            SunlightLineStyle            0
            SunlightLineWidth            2
            FillSunlight                 On
            SunlightFillColor            #ffffff
            SunlightMinOpacity           0.000000
            SunlightMaxOpacity           0.200000
            UmbraMaxOpacity              0.700000
            UmbraMinOpacity              0.400000
        END LightingData

        ShowDtedRegions     Off

        End Style

    END MapStyles

END MapData

        BEGIN GfxClassPref

        END GfxClassPref


        BEGIN ConnectGraphicsOptions

            AsyncPickReturnUnique          OFF

        END ConnectGraphicsOptions

    END Graphics
    
    BEGIN Overlays
    END Overlays
    
    BEGIN VO
    END VO
    
    BEGIN DIS

		Begin General

			Verbose                    Off
			Processing                 Off
			Statistics                 Off
			ExerciseID                 -1
			ForceID                    -1

		End General


		Begin Output

			Version                    5
			ExerciseID                 1
			forceID                    1
			HeartbeatTimer             5.000000
			DistanceThresh             1.000000
			OrientThresh               3.000000

		End Output


		Begin Time

			Mode                       rtPDUTimestamp

		End Time


		Begin PDUInfo


		End PDUInfo


		Begin Parameters

			ParmData  COLORFRIENDLY        blue
			ParmData  COLORNEUTRAL         white
			ParmData  COLOROPFORCE         red
			ParmData  MAXDRELSETS          1000

		End Parameters


		Begin Network

			NetIF                      Default
			Mode                       Broadcast
			McastIP                    224.0.0.1
			Port                       3000
			rChannelBufferSize         65000
			ReadBufferSize             1500
			QueuePollPeriod            20
			MaxRcvQueueEntries         1000
			MaxRcvIOThreads            4
			sChannelBufferSize         65000

		End Network


		Begin EntityTypeDef


#			order: kind:domain:country:catagory:subCatagory:specific:xtra ( -1 = * )


		End EntityTypeDef


		Begin EntityFilter
			Include                    *:*:*
		End EntityFilter

    END DIS

END Extensions

BEGIN SubObjects

Class AdvCAT

	AdvCAT1

END Class

Class Satellite

	ADEOS-II_27597
	ADEOS_24277
	AISAT_40054
	ASTROSAT_40930
	CARTOSAT-1_28649
	CARTOSAT-2_29710
	CARTOSAT-2A_32783
	CARTOSAT-2B_36795
	CARTOSAT-2C_41599
	CARTOSAT-2D_41948
	DUBAISAT-1_35682
	DUBAISAT-2_39419
	DZZ-HR_39731
	FAISAT_23465
	FAISAT_2V_24954
	GEODETIC_EARTH_07734
	GEOS_2_03093
	GEOSAT_15595
	JUGNU_37839
	KAZEOSAT_2_40010
	NEOSSAT_39089
	QSAT-EOS_40301
	RISAT-1_38248
	RISAT-2_34807
	SARAL_39086
	SCATSAT_1_41790
	SRMSAT_37841
	SWAYAM_41607
	TELEOS_1_41169
	THEOS_33396
	TIGRISAT_40043
	WEOS_27599
	YOUTHSAT_37388

END Class

END SubObjects

BEGIN References
    Instance *
        *
    END Instance
    Instance AdvCAT/AdvCAT1
    END Instance
    Instance Satellite/ADEOS-II_27597
        AdvCAT/AdvCAT1
        Satellite/ADEOS-II_27597
    END Instance
    Instance Satellite/ADEOS_24277
        AdvCAT/AdvCAT1
        Satellite/ADEOS_24277
    END Instance
    Instance Satellite/AISAT_40054
        AdvCAT/AdvCAT1
        Satellite/AISAT_40054
    END Instance
    Instance Satellite/ASTROSAT_40930
        AdvCAT/AdvCAT1
        Satellite/ASTROSAT_40930
    END Instance
    Instance Satellite/CARTOSAT-1_28649
        AdvCAT/AdvCAT1
        Satellite/CARTOSAT-1_28649
    END Instance
    Instance Satellite/CARTOSAT-2A_32783
        AdvCAT/AdvCAT1
        Satellite/CARTOSAT-2A_32783
    END Instance
    Instance Satellite/CARTOSAT-2B_36795
        AdvCAT/AdvCAT1
        Satellite/CARTOSAT-2B_36795
    END Instance
    Instance Satellite/CARTOSAT-2C_41599
        AdvCAT/AdvCAT1
        Satellite/CARTOSAT-2C_41599
    END Instance
    Instance Satellite/CARTOSAT-2D_41948
        AdvCAT/AdvCAT1
        Satellite/CARTOSAT-2D_41948
    END Instance
    Instance Satellite/CARTOSAT-2_29710
        AdvCAT/AdvCAT1
        Satellite/CARTOSAT-2_29710
    END Instance
    Instance Satellite/DUBAISAT-1_35682
        AdvCAT/AdvCAT1
        Satellite/DUBAISAT-1_35682
    END Instance
    Instance Satellite/DUBAISAT-2_39419
        AdvCAT/AdvCAT1
        Satellite/DUBAISAT-2_39419
    END Instance
    Instance Satellite/DZZ-HR_39731
        AdvCAT/AdvCAT1
        Satellite/DZZ-HR_39731
    END Instance
    Instance Satellite/FAISAT_23465
        AdvCAT/AdvCAT1
        Satellite/FAISAT_23465
    END Instance
    Instance Satellite/FAISAT_2V_24954
        AdvCAT/AdvCAT1
        Satellite/FAISAT_2V_24954
    END Instance
    Instance Satellite/GEODETIC_EARTH_07734
        AdvCAT/AdvCAT1
        Satellite/GEODETIC_EARTH_07734
    END Instance
    Instance Satellite/GEOSAT_15595
        AdvCAT/AdvCAT1
        Satellite/GEOSAT_15595
    END Instance
    Instance Satellite/GEOS_2_03093
        AdvCAT/AdvCAT1
        Satellite/GEOS_2_03093
    END Instance
    Instance Satellite/JUGNU_37839
        AdvCAT/AdvCAT1
        Satellite/JUGNU_37839
    END Instance
    Instance Satellite/KAZEOSAT_2_40010
        AdvCAT/AdvCAT1
        Satellite/KAZEOSAT_2_40010
    END Instance
    Instance Satellite/NEOSSAT_39089
        AdvCAT/AdvCAT1
        Satellite/NEOSSAT_39089
    END Instance
    Instance Satellite/QSAT-EOS_40301
        AdvCAT/AdvCAT1
        Satellite/QSAT-EOS_40301
    END Instance
    Instance Satellite/RISAT-1_38248
        AdvCAT/AdvCAT1
        Satellite/RISAT-1_38248
    END Instance
    Instance Satellite/RISAT-2_34807
        AdvCAT/AdvCAT1
        Satellite/RISAT-2_34807
    END Instance
    Instance Satellite/SARAL_39086
        AdvCAT/AdvCAT1
        Satellite/SARAL_39086
    END Instance
    Instance Satellite/SCATSAT_1_41790
        AdvCAT/AdvCAT1
        Satellite/SCATSAT_1_41790
    END Instance
    Instance Satellite/SRMSAT_37841
        AdvCAT/AdvCAT1
        Satellite/SRMSAT_37841
    END Instance
    Instance Satellite/SWAYAM_41607
        AdvCAT/AdvCAT1
        Satellite/SWAYAM_41607
    END Instance
    Instance Satellite/TELEOS_1_41169
        AdvCAT/AdvCAT1
        Satellite/TELEOS_1_41169
    END Instance
    Instance Satellite/THEOS_33396
        AdvCAT/AdvCAT1
        Satellite/THEOS_33396
    END Instance
    Instance Satellite/TIGRISAT_40043
        AdvCAT/AdvCAT1
        Satellite/TIGRISAT_40043
    END Instance
    Instance Satellite/WEOS_27599
        AdvCAT/AdvCAT1
        Satellite/WEOS_27599
    END Instance
    Instance Satellite/YOUTHSAT_37388
        *
        AdvCAT/AdvCAT1
        Satellite/YOUTHSAT_37388
    END Instance
END References

END Scenario
