import React from 'react';
import PropTypes from 'prop-types';
import clsx from 'clsx';
import {
  Box,
  Button,
  Modal, Fade,
  makeStyles,Backdrop, TextField
} from '@material-ui/core';
import { addSubject } from '../../../api/instructorAPI';

const useStyles = makeStyles((theme) => ({
  modal: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  paper: {
    backgroundColor: theme.palette.background.paper,
    border: '2px solid #000',
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
  },
  button: {
    '& > *': {
      margin: theme.spacing(1),
    }},
}));

const Toolbar = ({ className, ...rest }) => {
  const classes = useStyles();
  const [open, setOpen] = React.useState(false);
  const [values, setValues] = React.useState({
    subjectCode:'',
    title:'',
    id: 0
  });

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = (prop) => (event) => {
		setValues({ ...values, [prop]: event.target.value });
	};

  const handleSubmit = async() => {
		console.log(values);
		await addSubject(values).then((res) => {
		  if (res.data == false) {
			alert('Please login to continue.');
			window.location.replace('/');
		  } else {
			window.location.reload();
		  }
		})
		.catch((error) => {
		  alert(error + ', Please login to continue.');
		  window.location.replace('/');
		});
	   
	  }


  return (
    <div
      className={clsx(classes.root, className)}
      {...rest}
    >
      <Box display="flex" justifyContent="flex-end">
        <Button color="primary" variant="contained" onClick={handleOpen}>
          Add Subject
        </Button>
        <Modal
          aria-labelledby="transition-modal-title"
          aria-describedby="transition-modal-description"
          className={classes.modal}
          open={open}
          closeAfterTransition
          BackdropComponent={Backdrop}
        >
          <Fade in={open}>
            <div className={classes.paper}>
              <h2 style={{fontFamily: 'Arial', textAlign:'center'}}> Add a Subject </h2>
              <TextField
                id="outlined-multiline-flexible"
                required
                fullWidth
                value={values.subjectCode}
                onChange={handleChange('subjectCode')}
                label="Subject Code"
                variant="outlined"
                style={{ marginTop:12}}
              />
              <TextField
                id="outlined-multiline-flexible"
                required
                fullWidth
                value={values.title}
                onChange={handleChange('title')}
                label="Subject Title"
                variant="outlined"
                style={{ marginTop:12}}
              />
              <div className={classes.button}>
                <Button size="small" variant="contained"  onClick={handleClose} style={{marginLeft:120,marginTop:12}}>
                            Cancel
                </Button>
                <Button size="small" variant="contained" color="secondary" onClick={handleSubmit} style={{marginLeft:20,marginTop:12}}>
                            Submit
                </Button>
              </div>
            </div>
          </Fade>
        </Modal>
      </Box>
    </div>
  );
};

Toolbar.propTypes = {
  className: PropTypes.string
};

export default Toolbar;
