<a name="readme-top"></a>

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Commits][commits]][commits]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]


<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/VolodymyrDA/ResourceService">
    <img src="images/logo.png" alt="Logo" width="300" height="300">
  </a>

<h3 align="center">Support UA Resource Service</h3>

  <p align="center"> 
    <br />
    <a href="https://resourceservice-production.up.railway.app/">View Deployed Demo</a>
    ·
    <a href="https://github.com/VolodymyrDA/ResourceService/issues">Report Bug</a>
    ·
    <a href="https://github.com/VolodymyrDA/ResourceService/issues">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul> 
        <li><a href="#installation">Installation</a></li> 
        <li><a href="#swagger-ui">Swagger UI</a></li> 
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->

## About The Project

[![Product Name Screen Shot][product-screenshot]](https://resourceservice-production.up.railway.app/)
<p style="font-size: 1.3em;">
Create a database of regional resource hubs by volunteers and organizations to provide refugees and volunteers with the necessary resources.
</p>
User role:

    User registration
    Creating specific resource requests
    Viewing confirmed requests and hub resources information
    Changing profile information

Hub role:

    Viewing requests for resources that can be satisfied
    Confirming resource requests
    Writing off resources from the warehouse
    Changing profile information
    Generating statistical reports:
        Summary information on the entire database for each resource, including total amount of residues
        Summary information on the entire database on the number of each resource, which is insufficient to fulfill unfinished queries
        Output of 10 popular resources (by number of orders)
        and export them to PDF, Word & Excel.

### Built With

<b>
Java 18 , Spring: Boot MVC Security Data, Freemarker, Flyway,
Lombok, PostgresSQL ,Mapstruct ,Gradle, HTML, CSS, JavaScript, Bootstrap, Jquery
Swagger
</b>
<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->

## Getting Started

To get a local copy up and running follow these simplesteps.

### Installation

1. For Google authentication get the API Key at [https://console.cloud.google.com](https://example.com)
2. Clone the repo
   ```sh
   git clone https://github.com/VolodymyrDA/ResourceService.git
   ```
3. Enter your API credentials in environment variables:
   ```sh
                CLIENT_ID: 'ENTER YOUR CLIENT ID'
            CLIENT_SECRET: 'ENTER YOUR CLIENT SECRET' 
   ```
4. Enter your Postgres database credentials in environment variables:
   ```sh
    DATABASE_URL: ENTER YOUR DATABASE URL
    PGUSER: ENTER YOUR DB USER
    PGPASSWORD: ENTER YOUR DB PGPASSWORD
   ```
5. Build project with Gradle
6. Run ProjectMain

### Swagger UI

```sh
/swagger-ui/index.html
```

(Only available for registered users).
<p align="right">(<a href="#readme-top">back to top</a>)</p>
<!-- USAGE EXAMPLES -->

## Usage

Let's say that a group of volunteers has come together to create a website that connects refugees and other volunteers with local organizations that can provide them with resources like food, shelter, education, and healthcare. They decide to use the website to create a database of regional resource hubs that can help refugees in different areas.

To get started, they create a homepage for the website that explains the mission of the project and provides
instructions for how to use it. They also create a registration form that allows organizations and volunteers to sign up
and provide information about their resources and services.

Once organizations and volunteers have registered, they can add their own resource hubs to the database by filling out a
form with details like the name of the organization, the location of the resource hub, and the types of resources
available. They can also include photos, videos, and other multimedia to help users get a better understanding of what
the resource hub offers.

As the database grows, the volunteers behind the website work to organize the information and make it easier for users
to find the resources they need. They create search functions that allow users to filter by location, type of resource,
and other criteria, and they create a map that displays all of the resource hubs in a given area.

Over time, the website becomes a valuable resource for refugees and volunteers alike, helping them to connect with local
organizations and find the resources they need to rebuild their lives in a new country. The volunteers continue to
update and improve the database, adding new features and making it even easier to use.
<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- ROADMAP -->

## Roadmap

- [x] RC candidate version
- [X] Google authentication
- [X] Tests
- [X] Deployment
- [ ] Post API integration
    - [ ] UKRPOSHTA
    - [ ] NOVAPOSTA

See the [open issues](https://github.com/github_username/repo_name/issues) for a full list of proposed features (and
known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also
simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- LICENSE -->

## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTACT -->

## Contact

Volodymyr DA - optimyzator@gmail.com

Project Link: [https://github.com/VolodymyrDA/ResourceService](https://github.com/VolodymyrDA/ResourceService)


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[contributors-shield]: https://img.shields.io/github/contributors/VolodymyrDA/ResourceService.svg?style=for-the-badge

[contributors-url]: https://github.com/VolodymyrDA/ResourceService/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/VolodymyrDA/ResourceService.svg?style=for-the-badge

[forks-url]: https://github.com/VolodymyrDA/ResourceService/network/members

[commits]: https://img.shields.io/github/commit-activity/y/VolodymyrDA/ResourceService?style=for-the-badge

[commits-url]: https://img.shields.io/github/commit-activity/y/VolodymyrDA/ResourceService?style=for-the-badge

[issues-shield]: https://img.shields.io/github/issues/VolodymyrDA/ResourceService.svg?style=for-the-badge

[issues-url]: https://github.com/VolodymyrDA/ResourceService/issues

[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge

[license-url]: https://github.com/gVolodymyrDA/ResourceService/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://www.linkedin.com/in/volodymyr-doloka-351226153/

[product-screenshot]: images/screenshot.png