# cryptopoc
Cryptography Proof of Concept

## Generate a Key Pair

```shell
> keytool -genkey -keyalg RSA -keysize 1024 -alias SecretKey -keystore keystore.pfx -storetype pkcs12
Enter keystore password:  
Re-enter new password: 
What is your first and last name?
  [Unknown]:  
What is the name of your organizational unit?
  [Unknown]:  
What is the name of your organization?
  [Unknown]:  
What is the name of your City or Locality?
  [Unknown]:  
What is the name of your State or Province?
  [Unknown]:  
What is the two-letter country code for this unit?
  [Unknown]:  
Is CN=Unknown, OU=Unknown, O=Unknown, L=Unknown, ST=Unknown, C=Unknown correct?
  [no]:  yes

```
 - Generate a small RSA key pair - 1024 bits
 - `SecretKey` is the key name in the file.
 - `keystore.pfx` is the \#PKCS12 store.
 - Java's keytool required a password, so I entered `abcd1234`.

## Notes
This POC assumes a small message that fits in a single block.
