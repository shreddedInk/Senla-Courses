// Основной класс Резюме
public class Resume {
    private String id;
    private String name;
    private String email;
    private String phone;
    private Summary summary; // Один к одному

    public Resume(String id, String name, String email, String phone, Summary summary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.summary = summary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public void displayResumeInfo() {
        System.out.println("Resume ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone);
        if (summary != null) {
            System.out.println("Summary: " + summary.getText());
        }
    }
}

// Краткое описание в резюме (Summary), связь 1:1 с Resume
class Summary {
    private String text;

    public Summary(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

// Класс Навыков (Skills), связь 1:N
class Skills {
    private String skillName;
    private String proficiencyLevel;

    public Skill(String skillName, String proficiencyLevel) {
        this.skillName = skillName;
        this.proficiencyLevel = proficiencyLevel;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(String proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public void displaySkillInfo() {
        System.out.println("Skill: " + skillName + ", Proficiency Level: " + proficiencyLevel);
    }
}

// Класс Опыт Работы (WorkExperience), связь 1:N
class WorkExperience {
    private String company;
    private String position;
    private int years;

    public WorkExperience(String company, String position, int years) {
        this.company = company;
        this.position = position;
        this.years = years;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void displayWorkExperience() {
        System.out.println("Company: " + company + ", Position: " + position + ", Years: " + years);
    }
}

// Класс Сертификаты (Certificate), связь M:N
class Certificate {
    private String certificateName;
    private String issuingOrganization;
    private String issueDate;

    public Certificate(String certificateName, String issuingOrganization, String issueDate) {
        this.certificateName = certificateName;
        this.issuingOrganization = issuingOrganization;
        this.issueDate = issueDate;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getIssuingOrganization() {
        return issuingOrganization;
    }

    public void setIssuingOrganization(String issuingOrganization) {
        this.issuingOrganization = issuingOrganization;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public void displayCertificateInfo() {
        System.out.println("Certificate: " + certificateName + ", Issued by: " + issuingOrganization +
                ", Date: " + issueDate);
    }
}

// Класс Связь M:N для Сертификатов и Резюме
class ResumeCertificate {
    private Resume resume;
    private Certificate certificate;

    public ResumeCertificate(Resume resume, Certificate certificate) {
        this.resume = resume;
        this.certificate = certificate;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public void displayResumeCertificateInfo() {
        System.out.println("Resume: " + resume.getName() + ", Certificate: " + certificate.getCertificateName());
    }
}
