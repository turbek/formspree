# Thurisa Mail

Add a working form to your website without having to write any back-end logic!

### How it works

```HTML
<form action="http://localhost:4567/your@email.com" method="POST">
    <input type="text" name="name">
    <input type="email" name="_replyto">
    <input type="text" name="message">
    <input type="submit" value="Send">
</form>
```

Just add the above form to your website with any number of inputfields, substitute your email and we will forward the form to your email.

Forget Java and Javascript - no need to write back-end logic, it works even on a static website.

### How to set it up

1. Change the action attribute of the form to contain your email.
2. You are ready to receive emails!

### Javadoc link
https://turbek.github.io/formspree/
