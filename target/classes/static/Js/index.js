function addSkill(skillName) {
    // Get the skills field element
    let skillsField = document.getElementById("skillsField");

    // Add the selected skill to the skills field
    if (skillsField.value === "") {
        skillsField.value = skillName;
    } else {
        skillsField.value += ", " + skillName;
    }
}