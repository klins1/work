/**
 * Created by hach on 2017-01-29.
 */
import express from 'express';
import post from './post';

const router = express.Router();
router.use('/post', post);

export default router;