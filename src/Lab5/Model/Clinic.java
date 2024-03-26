package Lab5.Model;
import java.util.ArrayList;
import java.util.Arrays;

public class Clinic {
    private  String clinicName;

    public Clinic(String clinicName) {
        setClinicName(clinicName);
    }


    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public void setClinicName(String clinicName){ this.clinicName = clinicName; }

    public String getClinicName(){ return clinicName; }
    public void addDoctor(Doctor doctor){
        doctors.add(doctor);
    }
    public void addPatient(Patient patient){
        patients.add(patient);
    }
    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
    }
    public void delDoctor(Doctor doctor){
        doctors.remove(doctor);
    }
    public void delPatient(Patient patient){
        patients.remove(patient);
    }
    public void delAppointment(Appointment appointment){
        appointments.remove(appointment);
    }
    public ArrayList<Doctor> getDoctors() {
        return doctors;
   }
    public ArrayList<Patient> getPatients() {
        return patients;
    }
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
    public void iniDoctors(){
        Clinician IvanovaEA = new Clinician("Иванова Елена Александровна","Отоларинголог", new ArrayList<>(Arrays.asList("Нос","Правое ухо","Левое ухо","Горло")),"012");
        Clinician NicolaevaRA = new Clinician("Николаева Рима Андреевна", "Терапевт", new ArrayList<>(Arrays.asList("Горло", "Уши", "Нос", "Правое ухо", "Левое ухо")), "012");
        Clinician MixailovaES = new Clinician("Михайлова Елена Семёновна", "Терапевт", new ArrayList<>(Arrays.asList("Горло", "Уши", "Нос", "Правое ухо", "Левое ухо")), "012");
        Clinician FedorovAS = new Clinician("Фёдоров Андрей Степанович", "Терапевт", new ArrayList<>(Arrays.asList("Горло", "Уши", "Нос", "Правое ухо", "Левое ухо")), "012");
        Clinician FedorovaES = new Clinician("Фёдорова Евгения Степановна", "Терапевт", new ArrayList<>(Arrays.asList("Горло", "Уши", "Нос", "Правое ухо", "Левое ухо")), "012");
        Clinician SuvorovaMS = new Clinician("Суворова Маргарита Семёновна", "Терапевт", new ArrayList<>(Arrays.asList("Горло", "Уши", "Нос", "Правое ухо", "Левое ухо")), "012");
        Clinician TarasovKV = new Clinician("Тарасов Константин Викторович", "Окулист", new ArrayList<>(Arrays.asList("Левый глаз", "Правый глаз")), "012");
        Clinician RomanovaKE = new Clinician("Романова Ксения Евгеньевна", "Дерматолог", new ArrayList<>(Arrays.asList("Кожа лица", "Кожа рук", "Кожа ног", "Кожа спины", "Кожа живота", "Кожа груди")), "012");
        Diagnostican PetrovaNE = new Diagnostican("Петрова Надежда Евгеньевна","Рентгенолог","Рентгенологическое исследование", new ArrayList<>(Arrays.asList("Правая рука","Левая рука","Правая нога","Левая нога")),"012");
        Diagnostican RurikSI = new Diagnostican("Рюрик Святослав Игоревич", "Врач УЗИ", "Ультразвуковое исследование", new ArrayList<>(Arrays.asList("Брюшная полость", "Левая почка", "Правая почка", "Мочевой пузырь", "Щитовидная железа")), "012");
        Diagnostican VinogradovaAA = new Diagnostican("Виноградова Александра Юрьевна", "Процедурная медсестра", "Анализ крови", new ArrayList<>(Arrays.asList("Кровь вены правой руки", "Кровь вены левой руки")), "012");

        doctors.add(IvanovaEA);
        doctors.add(FedorovaES);
        doctors.add(SuvorovaMS);
        doctors.add(TarasovKV);
        doctors.add(RomanovaKE);
        doctors.add(PetrovaNE);
        doctors.add(RurikSI);
        doctors.add(VinogradovaAA);
        doctors.add(FedorovAS);
        doctors.add(MixailovaES);
        doctors.add(NicolaevaRA);
    }
    public void iniPatients(){
       Patient PetrovAA = new Patient("Петров Андрей Алексеевич", 1998, "123");
       Patient PavlovMV = new Patient("Павлов Михаил Васильевич", 1993,"123");
       Patient ParacetomolovIP = new Patient("Парацетомолов Игорь Петрович", 1991, "123");
       Patient BraginaVA = new Patient("Брагина Валентина Александровна", 1980, "123");
       Patient ElochkinaZK = new Patient("Елочкина Зинаида Константиновна", 2000, "123");
       Patient ZumerovAD = new Patient("Зумеров Алексей Дмитриевич", 2008, "123");

       patients.add(PetrovAA);
       patients.add(PavlovMV);
       patients.add(ParacetomolovIP);
       patients.add(BraginaVA);
       patients.add(ElochkinaZK);
       patients.add(ZumerovAD);
   }
    public void iniAppointments(){
       AppointmentOfClinician app1 = new AppointmentOfClinician(doctors.get(0), patients.get(0),"26.12.2023","14:00");
       AppointmentOfDiagnostican app2 = new AppointmentOfDiagnostican(doctors.get(5), patients.get(0)," 27.12.2023","14:00");
       AppointmentOfClinician app3 = new AppointmentOfClinician(doctors.get(0), patients.get(0),"28.12.2023","14:00");
       AppointmentOfDiagnostican app4 = new AppointmentOfDiagnostican(doctors.get(5), patients.get(0)," 29.12.2023","14:00");

       appointments.add(app1);
       app1.setComplite(true);
       app1.setComplaints("Затрудненное дыхание, насморк, боль в горле");
       app1.setExamines("носовые ходы сужены\nбез патологий\nбез патологий\nслизистые ярко-красные\n");
       app1.setDiagnosis("Риносинусит, фарингит");
       app1.setTreatment("Спрей 3 раза в день, таблетки 2 раза в день");
       appointments.add(app2);
       app2.setComplite(true);
       app2.setResearch("Правая рука");
       app2.setConclusion("Перелом правой руки в области локтевого сустава");
       appointments.add(app3);
       appointments.add(app4);

   }
}
