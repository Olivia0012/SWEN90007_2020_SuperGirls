import React, { useEffect } from 'react';
import { Link as RouterLink, useLocation,useRoutes } from 'react-router-dom';
import PropTypes from 'prop-types';
import routes from 'src/routes';
import {
  Avatar,
  Box,
  Divider,
  Drawer,
  Hidden,
  List,
  Typography,
  makeStyles
} from '@material-ui/core';
import {
  AlertCircle as AlertCircleIcon,
  BarChart as BarChartIcon,
  Lock as LockIcon,
  Settings as SettingsIcon,
  ShoppingBag as ShoppingBagIcon,
  User as UserIcon,
  UserPlus as UserPlusIcon,
  Users as UsersIcon
} from 'react-feather';
import NavItem from './NavItem';
import {loginUser} from '../../../views/auth/LoginView';
 




const useStyles = makeStyles(() => ({
  mobileDrawer: {
    width: 256
  },
  desktopDrawer: {
    width: 256,
    top: 64,
    height: 'calc(100% - 64px)'
  },
  avatar: {
    cursor: 'pointer',
    width: 64,
    height: 64
  }
}));

const NavBar = ({ onMobileClose, openMobile }) => {
  const classes = useStyles();
  const location = useLocation();
  const routeResult = useRoutes(routes);
  var name = '';
  var jobTitle = '';
  var session = '';
  if(routeResult !== null){
    const userInfo = routeResult.props.value.params;
    name = userInfo.name;
    jobTitle = userInfo.role;
 }
 
  console.log(location);
  console.log(routeResult);
  console.log(loginUser);
  
 
 
  
  const [user,setUser] = React.useState({
    avatar: '/static/images/avatars/avatar_6.png',
	  jobTitle: jobTitle,
	  name: name,
    });
  console.log(user);

  const items = [
    {
      href: './subjects',
      icon: ShoppingBagIcon,
      title: 'Subjects'
    },
    {
      href: './exams/user='+`${user.user}`,
      icon: SettingsIcon,
      title: 'Adding Exam'
    },
    {
      href: './students/user='+`${user.user}`,
      icon: SettingsIcon,
      title: 'Students'
    }
  ];
  
  console.log(user);

  useEffect(() => {
    if (openMobile && onMobileClose) {
      onMobileClose();
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [location.pathname]);

  const content = (
    <Box
      height="100%"
      display="flex"
      flexDirection="column"
    >
      <Box
        alignItems="center"
        display="flex"
        flexDirection="column"
        p={2}
      >
        <Avatar
          className={classes.avatar}
          component={RouterLink}
          src={user.avatar}
          to="/app/account"
        />
        <Typography
          className={classes.name}
          color="textPrimary"
          variant="h5"
        >
          {user.name}
        </Typography>
        <Typography
          color="textSecondary"
          variant="body2"
        >
          {user.jobTitle}
        </Typography>
      </Box>
      <Divider />
      <Box p={2}>
        <List>
          {items.map((item) => (
            <NavItem
              href={item.href}
              key={item.title}
              title={item.title}
              icon={item.icon}
            />
          ))}
        </List>
      </Box>
      <Box flexGrow={1} />
      { /*
      <Box
        p={2}
        m={2}
        bgcolor="background.dark"
      >
      
        <Typography
          align="center"
          gutterBottom
          variant="h4"
        >
         111 Need more?
        </Typography>
       
        <Typography
          align="center"
          variant="body2"
        >
          Upgrade to PRO version and access 20 more screens
        </Typography>
        <Box
          display="flex"
          justifyContent="center"
          mt={2}
        >
          <Button
            color="primary"
            component="a"
            href="https://react-material-kit.devias.io"
            variant="contained"
          >
            See PRO version
          </Button>
         
        </Box> 
       
      </Box>*/}
    </Box>
  );

  return (
    <>
      <Hidden lgUp>
        <Drawer
          anchor="left"
          classes={{ paper: classes.mobileDrawer }}
          onClose={onMobileClose}
          open={openMobile}
          variant="temporary"
        >
          {content}
        </Drawer>
      </Hidden>
      <Hidden mdDown>
        <Drawer
          anchor="left"
          classes={{ paper: classes.desktopDrawer }}
          open
          variant="persistent"
        >
          {content}
        </Drawer>
      </Hidden>
    </>
  );
};

NavBar.propTypes = {
  onMobileClose: PropTypes.func,
  openMobile: PropTypes.bool
};

NavBar.defaultProps = {
  onMobileClose: () => {},
  openMobile: false
};

export default NavBar;
