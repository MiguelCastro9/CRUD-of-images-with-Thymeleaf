imgFile.onchange = evt => {
    const [file] = imgFile.files;
    if (file) {
        img.src = URL.createObjectURL(file);
    }
};


