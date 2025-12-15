---

## 📄 Dockerfile.template
```dockerfile
# TODO: Use Java 17 base image
FROM __________

# TODO: Set working directory
WORKDIR __________

# TODO: Copy the JAR file from Maven target directory
# Hint: The JAR file is created in target/ directory with name simple-inventory-1.0.0.jar
COPY __________ __________

# TODO: Expose port (optional for CLI application)
# EXPOSE ____

# TODO: Run the application
# Hint: Use java -jar command to run the JAR file
CMD ["__________", "__________", "__________"]