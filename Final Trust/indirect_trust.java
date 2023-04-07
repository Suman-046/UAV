import java.util.ArrayList;

class IndirectTrustCalculator {
    public static double calculateIndirectTrust(String personA, String personB, ArrayList<Interaction> interactions) {
        ArrayList<String> mutualContacts = findMutualContacts(personA, personB, interactions);
        double trustScore = 0.0;
        
        for (String contact : mutualContacts) {
            double contactTrust = calculateDirectTrust(personA, contact, interactions);
            trustScore += contactTrust;
        }
        
        return trustScore / mutualContacts.size();
    }
    
    private static ArrayList<String> findMutualContacts(String personA, String personB, ArrayList<Interaction> interactions) {
        ArrayList<String> contactsA = getContacts(personA, interactions);
        ArrayList<String> contactsB = getContacts(personB, interactions);
        contactsA.retainAll(contactsB);
        return contactsA;
    }
    
    private static ArrayList<String> getContacts(String person, ArrayList<Interaction> interactions) {
        ArrayList<String> contacts = new ArrayList<>();
        
        for (Interaction interaction : interactions) {
            if (interaction.getPersonA().equals(person)) {
                contacts.add(interaction.getPersonB());
            } else if (interaction.getPersonB().equals(person)) {
                contacts.add(interaction.getPersonA());
            }
        }
        
        return contacts;
    }
    
    private static double calculateDirectTrust(String personA, String personB, ArrayList<Interaction> interactions) {
        double trustScore = 0.0;
        int numInteractions = 0;
        
        for (Interaction interaction : interactions) {
            if ((interaction.getPersonA().equals(personA) && interaction.getPersonB().equals(personB)) ||
                    (interaction.getPersonA().equals(personB) && interaction.getPersonB().equals(personA))) {
                trustScore += interaction.getTrustScore();
                numInteractions++;
            }
        }
        
        return trustScore / numInteractions;
    }
}

class Interaction {
    private String personA;
    private String personB;
    private double trustScore;
    
    public Interaction(String personA, String personB, double trustScore) {
        this.personA = personA;
        this.personB = personB;
        this.trustScore = trustScore;
    }
    
    public String getPersonA() {
        return personA;
    }
    
    public String getPersonB() {
        return personB;
    }
    
    public double getTrustScore() {
        return trustScore;
    }
}
public class indirect_trust{
    public static void main(String[] args) {
        IndirectTrustCalculator in = new IndirectTrustCalculator();
        in.calculateIndirectTrust("A","B" , true);
    }
}

