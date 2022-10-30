package transfer.money.conversion.dtos;

import java.io.Serializable;



public class OwnerDto implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Long id;
	    private String firstName;
	    private String lastName;
	    private String immatriculationSocialNumber;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getImmatriculationSocialNumber() {
			return immatriculationSocialNumber;
		}
		public void setImmatriculationSocialNumber(String immatriculationSocialNumber) {
			this.immatriculationSocialNumber = immatriculationSocialNumber;
		}
	    public OwnerDto() {
		super();
	}
		
		public OwnerDto(Long id, String firstName, String lastName, String immatriculationSocialNumber) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.immatriculationSocialNumber = immatriculationSocialNumber;
		}
		@Override
		public String toString() {
			return "OwnerDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", immatriculationSocialNumber=" + immatriculationSocialNumber + "]";
		}
	    
	    

}
