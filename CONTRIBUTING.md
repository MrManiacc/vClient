## Contributing
Contributions are needed ot keep an Open Source project alive; However, we recognize the need to legally
protect all contributors and project staff. To that effect, we are requiring that contributors follow the
basic guidelines set forth in this outline.

### Licensing & CLA
We take copyrights and intellectual property very seriously. We want to protect everyone's rights. In
order to achieve this goal, the project requires the signoff of a Contributor Licensing Agreement which 
states that you agree that your contributions are **non-exclusive, permanent, irrevocable, unlimited license to use, publish, or re-publish your Content in connection with the Service**,
the CLA **must be signed before contributing to the project!**

The [Contributor Licensing Agreement](https://www.clahub.com/agreements/Vizun/vClient) can be located [here](https://www.clahub.com/agreements/Vizun/vClient).

For your reference, the project is licensed under the [Apache 2 License](license.md).

### Code Style
A less significant, however important part of the project is the adoption of a code style. We have adopted the 
[Google Java Code Style](https://google-styleguide.googlecode.com/svn/trunk/javaguide.html) for the project.
More information on this adoption can be found on [the project website](https://vizun.org/index.php?threads/adoption-of-a-code-style.29/).

#### Commit Messages & Issue Management
We have adopted [JIRA](https://vizunproject.atlassian.net) as our internal bug tracker. In order to make
the Pull Request team's workflow easier - we require that you include in your commit message the fixes or
additions that you make based on the JIRA issue number. 

Examples:

FIXES CLI-102
FIXES API-42
ADDS LAUNCH-74

In addition to including the JIRA reference number, please include sufficient information on what is changed and what the intended
results are for your commits. Please try and keep the number of commits in a pull request minimal and only submit a pull request with
related changes.

#### Pull Request Guidelines
We thrive on independent developers submitting pull requests with new features and bug fixes; without these contributions the 
project would not be able to function. However, we require some basic guidelines to be followed:
1. A CLA Must be signed off.
2. The Commit message must include sufficient information and the fixes.
3. New features must be submitted in their *own* pull requests.
    * Bug fixes do not need be in their own Pull requests, however must be independent of new features.
4. Code must pass a CI check.
5. Code must not contain malicious content.